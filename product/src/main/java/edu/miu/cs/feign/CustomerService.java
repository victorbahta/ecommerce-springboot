package edu.miu.cs.feign;

import edu.miu.cs.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("CUSTOMERSERVICE")
public interface CustomerService {

    @GetMapping(value = "/customers/{customerId}")
    public CustomerDTO getCustomerById(@PathVariable Integer customerId);
}
