package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.contract.OrderDto;
import edu.miu.cs.cs544.domain.Order;
import edu.miu.cs.cs544.service.OrderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;
    @Operation(summary = "Create new order for customer")

    @PostMapping("/{customerId}/products/{productId}")
    public ResponseEntity<?> createOrder(@RequestBody Order order,
                                         @PathVariable("customerId") Integer customerId,
                                         @PathVariable("productId") Integer productId) {
        if(customerId == 0 || productId == 0)
            return new ResponseEntity<>("Invalid customer ID or proudct ID", HttpStatus.BAD_REQUEST);


        OrderDto orderDto = orderService.createOrder(order, productId, customerId);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all orders of a customer")
    @GetMapping("/customers/{customerID}")
    public Page<OrderDto> getOrders(@PathVariable("customerID") Integer customerID) {
        return orderService.getOrders(customerID, Pageable.ofSize(20));
    }


    @Operation(summary = "Get single order detail")
    @GetMapping("/{orderID}")
    public OrderDto getOrder(@PathVariable("orderID") Integer orderID) {
        return orderService.getOrder(orderID);
    }

    @Operation(summary = "Change order status")
    @PutMapping("/{orderID}/status")
    public ResponseEntity<String> returnOrder(@PathVariable("orderID") Integer orderID,
            @RequestParam("status") String status) {
        String response = orderService.changeOrderStatus(orderID, status);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Return an order")
    @PutMapping("/{orderID}")
    public ResponseEntity<String> returnOrder(@PathVariable("orderID") Integer orderID) {
        String response = orderService.returnOrder(orderID);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cancel an order")
    @DeleteMapping("/{orderID}")
    public ResponseEntity<String> cancelOrder(@PathVariable("orderID") Integer orderID) {
        String response = orderService.cancelOrder(orderID);
        return ResponseEntity.ok(response);
    }



}
