package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.contract.OrderDto;
import edu.miu.cs.cs544.contract.OrderResponse;
import edu.miu.cs.cs544.domain.Cart;
import edu.miu.cs.cs544.domain.Order;
import edu.miu.cs.cs544.domain.OrderLineItem;
import edu.miu.cs.cs544.domain.OrderStatus;
import edu.miu.cs.cs544.repository.CartRepository;
import edu.miu.cs.cs544.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
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

    public OrderDto createOrder(Order order, Integer productId, Integer customerID) {
        order.setCustomerId(customerID);
//        order.setProductId(productId);
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
