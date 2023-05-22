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
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderRepository orderRepository;

    public OrderDto createOrder(Order order, Integer productId, Integer customerID) {
        order.setCustomerId(customerID);
        order.setProductId(productId);
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.New);
        Order o = orderRepository.save(order);

        return modelMapper.map(o, OrderDto.class);

    }

    public OrderDto getOrder(Integer orderID) {
        Optional<Order> order = orderRepository.findById(orderID);
        return order.map(value -> modelMapper.map(value, OrderDto.class)).orElse(null);

    }

    public Page<OrderDto> getOrders(Integer customerId, Pageable pageable) {
        Page<Order> orders = orderRepository.findByCustomerId(customerId, pageable);
        return orders.map(order -> modelMapper.map(order, OrderDto.class));

    }



    public String returnOrder(Integer orderID) {
        Order order = orderRepository.findById(orderID).orElse(new Order());
        order.setStatus(OrderStatus.Returned);
        Order o = orderRepository.save(order);
        if(o.getStatus().equals(OrderStatus.Returned)) return "Order returned successfully";

        return "Failed to return order";

    }

    public String cancelOrder(Integer orderID) {
        orderRepository.deleteById(orderID);
        Optional<Order> o  = orderRepository.findById(orderID);

        if(o.isEmpty()) return "Order cancelled successfully";

        return "Failed to cancel order";

    }

    public String changeOrderStatus(Integer orderID, String status) {
        Order order = orderRepository.findById(orderID).orElse(new Order());
        order.setStatus(OrderStatus.valueOf(status));
        Order o = orderRepository.save(order);
        if(o.getStatus().equals(OrderStatus.valueOf(status))) return "Order status updated successfully";

        return "Failed to update order";
    }


}
