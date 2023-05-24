package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.contract.OrderDto;
import edu.miu.cs.cs544.domain.Order;
import edu.miu.cs.cs544.domain.OrderStatus;
import edu.miu.cs.cs544.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Converter<Order, OrderDto> orderConverter;

    public OrderDto getOrder(Integer orderID) {
        Optional<Order> order = orderRepository.findById(orderID);
        return order.map(orderConverter::toDto).orElse(null);
    }

    public Page<OrderDto> getOrders(Integer customerId, Pageable pageable) {
        Page<Order> orders = orderRepository.findByCustomerId(customerId, pageable);
        return orders.map(orderConverter::toDto);
    }

    @Override
    public Boolean getOrdersContainsProduct(Integer customerId, Integer orderId, Integer productId) {
        return orderRepository.findByCustomerAndContainsProduct(customerId, orderId, productId) != null;
    }

    public OrderDto returnOrder(Integer orderId) throws Exception {
        return changeStatus(orderId, OrderStatus.Returned);
    }

    public OrderDto cancelOrder(Integer orderId) throws Exception {
        return changeStatus(orderId, OrderStatus.Cancelled);
    }

    public OrderDto changeStatus(Integer orderId, OrderStatus status) throws Exception {
        return orderRepository.findById(orderId)
                .map(o -> {
                    o.setStatus(status);
                    updateOrderBasedOnStatus(o);
                    return orderRepository.save(o);
                })
                .map(orderConverter::toDto)
                .orElseThrow(() -> new Exception("Cannot find order with id " + orderId));
    }

    private void updateOrderBasedOnStatus(Order o) {
        if (Objects.requireNonNull(o.getStatus()) == OrderStatus.Delivered) {
            o.setDeliveryDate(LocalDate.now());
        }
    }
}
