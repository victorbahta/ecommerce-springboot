package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.contract.OrderDto;
import edu.miu.cs.cs544.domain.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderDto> getOrders(Integer customerId, Pageable size);

    Boolean getOrdersContainsProduct(Integer customerId, Integer orderId, Integer productId);

    OrderDto getOrder(Integer orderId);

    OrderDto changeStatus(Integer orderId, OrderStatus status) throws Exception;

    OrderDto returnOrder(Integer orderId) throws Exception;

    OrderDto cancelOrder(Integer orderId) throws Exception;
}
