package edu.miu.cs.feign;

import edu.miu.cs.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("ORDERSERVICE")
public interface OrderService {
    @GetMapping(value = "/orders/{customerId}")
    public Boolean checkProductOrderedByCustomer(@PathVariable Integer customerId);
}
