package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.contract.OrderDto;
import edu.miu.cs.cs544.domain.OrderStatus;
import edu.miu.cs.cs544.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders API", description = "Order Controller")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Operation(summary = "Get all orders of a customer")
    @GetMapping
    public Page<OrderDto> getOrders(@RequestHeader("userId") Integer customerId) {
        return orderService.getOrders(customerId, Pageable.ofSize(20));
    }


    @Operation(summary = "Get single order detail")
    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Integer orderId) {
        return orderService.getOrder(orderId);
    }

    @Operation(summary = "Change order status")
    @PostMapping("/{orderId}/change-status")
    public OrderDto changeStatus(@PathVariable("orderId") Integer orderId,
            @RequestParam("status") String status) {
       if(!EnumUtils.isValidEnum(OrderStatus.class, StringUtils.capitalize(status)))
           return null;
        try {
            return orderService.changeStatus(orderId, OrderStatus.valueOf(status));
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @Operation(summary = "Return an order")
    @PutMapping("/{orderId}/return")
    public OrderDto returnOrder(@PathVariable("orderId") Integer orderId) {
        try {
            return orderService.returnOrder(orderId);
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @Operation(summary = "Cancel an order")
    @PutMapping("/{orderId}/cancel")
    public OrderDto cancelOrder(@PathVariable("orderId") Integer orderId) {
        try {
            return orderService.cancelOrder(orderId);
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @Operation(summary = "Return whether customer places any order with productId")
    @GetMapping("/verified")
    public Boolean getOrdersContainsProduct(@RequestParam("customerId") Integer customerId,
                                            @RequestParam("orderId") Integer orderId,
                                            @RequestParam("productId") Integer productId) {
        return orderService.getOrdersContainsProduct(customerId, orderId, productId);
    }
}
