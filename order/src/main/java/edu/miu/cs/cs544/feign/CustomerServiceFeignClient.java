package edu.miu.cs.cs544.feign;

import edu.miu.cs.cs544.config.FeignConfig;
import edu.miu.cs.cs544.contract.AddressDto;
import edu.miu.cs.cs544.contract.CreditCardDto;
import edu.miu.cs.cs544.contract.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "${api-client.customer.name}", url = "${api-client.customer.url}", configuration = FeignConfig.class)
public interface CustomerServiceFeignClient {

    @GetMapping(value = "/customers/{customerId}")
    Optional<CustomerDto> findCustomerById(@PathVariable("customerId") Integer customerId);

    @GetMapping(value = "/customers/{customerId}/addresses/{addressId}")
    Optional<AddressDto> findAddressById(@PathVariable("customerId") Integer customerId, @PathVariable("addressId") Integer addressId);
    @GetMapping(value = "/customers/{customerId}/credit-cards/{creditCardId}")
    Optional<CreditCardDto> findCreditCardById(@PathVariable("customerId") Integer customerId, @PathVariable("creditCardId") Integer creditCardId);
}