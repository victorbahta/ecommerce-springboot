package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.contract.OrderDto;
import edu.miu.cs.cs544.domain.OrderStatus;
import edu.miu.cs.cs544.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Tag(name = "Order API", description = "Order Controller")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Operation(summary = "Get all orders of a customer")
    @GetMapping
    public Page<OrderDto> getOrders(@RequestHeader("customerId") Integer customerId) {
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
            @RequestParam("status") String status) throws Exception {
        return orderService.changeStatus(orderId, OrderStatus.valueOf(status));
    }

    @Operation(summary = "Return an order")
    @PostMapping("/{orderId}/return")
    public OrderDto returnOrder(@PathVariable("orderId") Integer orderId) throws Exception {
        return orderService.returnOrder(orderId);
    }

    @Operation(summary = "Cancel an order")
    @PostMapping("/{orderId}/cancel")
    public OrderDto cancelOrder(@PathVariable("orderId") Integer orderId) throws Exception {
        return orderService.cancelOrder(orderId);
    }
}
