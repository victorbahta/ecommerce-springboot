package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.contract.OrderDto;

public interface OrderService {
    OrderDto placeOrder(Integer cartId);
}
