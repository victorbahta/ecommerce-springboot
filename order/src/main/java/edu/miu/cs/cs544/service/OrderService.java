package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.contract.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(Integer cartId);
}
