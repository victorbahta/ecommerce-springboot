package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.contract.OrderResponse;
import edu.miu.cs.cs544.domain.Order;
import edu.miu.cs.cs544.domain.OrderStatus;
import edu.miu.cs.cs544.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderRepository orderRepository;

    public OrderResponse createOrder(Order order) {
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.New);
        Order o = orderRepository.save(order);

        return modelMapper.map(o, OrderResponse.class);

    }

    public Page<OrderResponse> getOrders(Integer customerId, Pageable pageable) {
        Page<Order> orders = orderRepository.findByCustomerId(customerId, pageable);
        return orders.map(order -> modelMapper.map(order,OrderResponse.class));

    }
}
