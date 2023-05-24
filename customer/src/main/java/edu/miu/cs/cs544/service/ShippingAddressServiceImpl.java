package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.CreditCard;
import edu.miu.cs.cs544.domain.address.Address;
import edu.miu.cs.cs544.domain.address.AddressType;
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
import java.util.concurrent.atomic.AtomicBoolean;

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

    public boolean addNewShippingAddress(Integer customerId, ShippingAddressDto shippingAddressDto){

        try{
            AtomicBoolean isSaved = new AtomicBoolean(false);
            var shippingAddress = modelMapper.map(shippingAddressDto, Address.class);
            shippingAddress.setType(AddressType.SHIPPING);

            customerRepository.findById(customerId).ifPresent(customer -> {

                if(shippingAddress.isDefault() == true){
                    customer.getShippingAddress().forEach(address -> {
                        address.setDefault(false);
                        address.setType(AddressType.SHIPPING);
                    });
                }
                customer.getShippingAddress().add(shippingAddress);
                customerRepository.save(customer);
                isSaved.set(true);
            });

            return isSaved.get();

        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}