package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.CreditCard;
import edu.miu.cs.cs544.domain.address.Address;
import edu.miu.cs.cs544.dto.CreditCardDto;
import edu.miu.cs.cs544.dto.ShippingAddressDto;
import edu.miu.cs.cs544.repository.AddressRepository;
import edu.miu.cs.cs544.repository.CreditCardRepository;
import edu.miu.cs.cs544.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingAddressServiceImpl implements  ShippingAddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ShippingAddressDto> findAll(Integer customerId){

        var shippingAddressDtoList = new ArrayList<ShippingAddressDto>();
        customerRepository.findById(customerId).ifPresent(customer -> {
            customer.getShippingAddress().forEach(shippingAddress -> {
                shippingAddressDtoList.add(modelMapper.map(shippingAddress, ShippingAddressDto.class));
            });
        });

        return shippingAddressDtoList;
    }

    public ShippingAddressDto findByShippingId(Integer customerId, Integer shippingId){
        var address = new Address();
        address = addressRepository.findByShippingId(customerId, shippingId);
        return modelMapper.map(address, ShippingAddressDto.class);
    }

}