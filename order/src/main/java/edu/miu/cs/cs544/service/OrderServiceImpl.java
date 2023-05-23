package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.contract.OrderResponse;
import edu.miu.cs.cs544.domain.Cart;
import edu.miu.cs.cs544.domain.Order;
import edu.miu.cs.cs544.domain.OrderLineItem;
import edu.miu.cs.cs544.domain.OrderStatus;
import edu.miu.cs.cs544.repository.CartRepository;
import edu.miu.cs.cs544.repository.OrderRepository;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private Converter<Order, OrderResponse> orderConverter;

    @Autowired
    private ShippingCostStrategy shippingCostStrategy;

    @Autowired
    private TaxCalculationStrategy taxCalculationStrategy;

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

    @Override
    public OrderResponse placeOrder(Integer cartId) {
        return cartRepository.findById(cartId).map(cart-> {
            calculateCart(cart);
            Order order = copyCartToOrder(cart);
            cartRepository.delete(cart);
            return orderRepository.save(order);
        }).map(orderConverter::toDto).orElse(null);
    }

    private void calculateCart(Cart cart) {
        Double subTotal = cart.getLineItems().stream().map(i -> i.getProduct().getPrice() * i.getQuantity()).reduce(0.0, Double::sum);
        cart.setSubTotal(subTotal);
        Double shippingCost = shippingCostStrategy.calculateShippingCost(cart);
        Double taxAmount = taxCalculationStrategy.calculateTax(cart);
        cart.setShippingCost(shippingCost);
        cart.setTaxAmount(taxAmount);
        cart.setTotal(subTotal + shippingCost + taxAmount);
    }

    private Order copyCartToOrder(Cart cart) {
//        return modelMapper.map(cart, Order.class);
        Order order = new Order();
        order.setCustomerId(cart.getCustomerId());
        order.setShippingAddress(cart.getShippingAddress());
        order.setCreditCard(cart.getCreditCard());
        order.setSubTotal(cart.getSubTotal());
        order.setShippingCost(cart.getShippingCost());
        order.setTaxAmount(cart.getTaxAmount());
        order.setTotal(cart.getTotal());
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.Placed);
        order.setLineItems(cart.getLineItems().stream().map(i -> {
            OrderLineItem lineItem = new OrderLineItem();
            lineItem.setProduct(i.getProduct());
            lineItem.setQuantity(i.getQuantity());
            lineItem.setDiscountValue(i.getDiscountValue());
            return lineItem;
        }).collect(Collectors.toList()));
        return order;
    }
}
