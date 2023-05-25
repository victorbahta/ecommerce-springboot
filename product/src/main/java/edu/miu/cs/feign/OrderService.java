package edu.miu.cs.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@FeignClient("ORDERSERVICE")
public interface OrderService {
    @GetMapping(value = "/orders/verified")
    Optional<Boolean> checkProductOrderedByCustomer(@RequestParam("customerId") Integer customerId, @RequestParam("orderId") Integer orderId, @RequestParam("productId") Integer productId);
}
