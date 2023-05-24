package edu.miu.cs.feign;

import edu.miu.cs.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient("CUSTOMERSERVICE")
public interface CustomerService {

    @GetMapping(value = "/customers/{customerId}")
    public CustomerDto getCustomerById(@PathVariable Integer customerId);
}
