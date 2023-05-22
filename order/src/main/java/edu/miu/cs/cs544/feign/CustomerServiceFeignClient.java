package edu.miu.cs.cs544.feign;

import edu.miu.cs.cs544.config.FeignConfig;
import edu.miu.cs.cs544.contract.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${api-client.name}", url = "${api-client.url}", configuration = FeignConfig.class)
public interface CustomerServiceFeignClient {

    @GetMapping(value = "/customers/{customerId}")
    CustomerResponse findCustomerById(@PathVariable("customerId") Integer customerId);
}