package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.contract.OrderResponse;
import edu.miu.cs.cs544.domain.Order;
import edu.miu.cs.cs544.service.OrderServiceImpl;
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
    @PostMapping("/{customerId}/products/{productId}")
    public ResponseEntity<?> createOrder(@RequestBody Order order,
                                         @PathVariable("customerId") Integer customerId,
                                         @PathVariable("productId") Integer productId) {
        if(customerId == 0 || productId == 0)
            return new ResponseEntity<>("Invalid customer ID or proudct ID", HttpStatus.BAD_REQUEST);

        order.setCustomerId(customerId);
        OrderResponse orderResponse = orderService.createOrder(order);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @GetMapping("/customers/{customerID}")
    public Page<OrderResponse> getOrders(@PathVariable("customerID") Integer customerID) {
        return orderService.getOrders(customerID, Pageable.ofSize(20));
    }



}
