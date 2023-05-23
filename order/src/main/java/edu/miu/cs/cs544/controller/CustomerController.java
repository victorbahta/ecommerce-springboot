package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.contract.AddressDto;
import edu.miu.cs.cs544.contract.CreditCardDto;
import edu.miu.cs.cs544.contract.CustomerDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @GetMapping(value = "/{customerId}")
    Optional<CustomerDto> findCustomerById(@PathVariable("customerId") Integer customerId) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerId);
        customerDto.setFirstName("Quoc Nam");
        customerDto.setLastName("Le");
        customerDto.setAdmin(false);
        customerDto.setEmail("QNLe@miu.edu");
        return Optional.of(customerDto);
    }

    @GetMapping(value = "/{customerId}/credit-cards/{creditCardId}")
    Optional<CreditCardDto> findCreditCardById(@PathVariable("customerId") Integer customerId, @PathVariable("creditCardId") Integer creditCardId) {
        CreditCardDto creditCardDto = new CreditCardDto();
        creditCardDto.setId(creditCardId);
        creditCardDto.setCreditNumber("123456789");
        creditCardDto.setName("Quoc Nam Le");
        creditCardDto.setSecurityCode("123");
        creditCardDto.setExpirationDate(LocalDate.now());
        return Optional.of(creditCardDto);
    }


    @GetMapping(value = "/{customerId}/addresses/{addressId}")
    Optional<AddressDto> findAddressById(@PathVariable("customerId") Integer customerId, @PathVariable("addressId") Integer addressId) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(addressId);
        addressDto.setStreet("801N 10th Street");
        addressDto.setCity("Fairfield");
        addressDto.setZip("52556");
        return Optional.of(addressDto);
    }
}
