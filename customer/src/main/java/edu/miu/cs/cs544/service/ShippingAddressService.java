package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.dto.CreditCardDto;
import edu.miu.cs.cs544.dto.ShippingAddressDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShippingAddressService {

    public List<ShippingAddressDto> findAll(Integer customerId);
    public ShippingAddressDto findByShippingId(Integer customerId, Integer shippingId);
}
