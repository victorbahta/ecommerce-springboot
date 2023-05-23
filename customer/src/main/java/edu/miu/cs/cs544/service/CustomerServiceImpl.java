package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.address.AddressType;
import edu.miu.cs.cs544.dto.CustomerCredentialDto;
import edu.miu.cs.cs544.dto.CustomerDto;
import edu.miu.cs.cs544.feign.AuthService;
import edu.miu.cs.cs544.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    AuthService authService;

    @Override
    public List<CustomerDto> findAll() {
        var customersDtoList = new ArrayList<CustomerDto>();
        customerRepo.findAll().forEach(cust -> {
            customersDtoList.add(mapCustomerToDto(cust));
        });
        return customersDtoList;
    }

    @Override
    public CustomerDto findById(Integer id) {
        return mapCustomerToDto(customerRepo.findById(id).orElse(null));
    }


    @Override
    public boolean addNewCustomer(CustomerDto customerDto) {

        try {
            var customer = mapDtoToCustomer(customerDto);

            if(customer.getBillingAddress() != null)
                customer.getBillingAddress().setType(AddressType.BILLING);

            if(customer.getShippingAddress() != null)
                customer.getShippingAddress().forEach(address -> {
                    address.setType(AddressType.SHIPPING);
                });

            Customer savedCustomer = customerRepo.save(customer);
//            log.info("Test {}", savedCustomer);

            /*** Calling to auth service to save the user credential ***/
            saveCredentialForAuthService(savedCustomer, customerDto);

            return true;

        }catch (Exception ex){
            log.error(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto, Integer customerId){
        try {
            var customer = mapDtoToCustomer(customerDto);
            customer.setId(customerId);

            if(customer.getBillingAddress() != null)
                customer.getBillingAddress().setType(AddressType.BILLING);

            if(customer.getShippingAddress() != null)
                customer.getShippingAddress().forEach(address -> {
                    address.setType(AddressType.SHIPPING);
                });

            Customer savedCustomer = customerRepo.save(customer);

            /*** Calling to auth service to save the user credential ***/
            saveCredentialForAuthService(savedCustomer, customerDto);
            log.info("Test {}", savedCustomer);

            return true;
        }catch (Exception ex){
            log.error(ex.getMessage());
            return false;
        }
    }


    @Override
    public boolean deleteById(Integer id) {
        customerRepo.deleteById(id);
        return true;
    }


    private void saveCredentialForAuthService(Customer savedCustomer, CustomerDto customerDto){
        CustomerCredentialDto customerCredentialDto = new CustomerCredentialDto();
        customerCredentialDto.setId(savedCustomer.getId());
        customerCredentialDto.setEmail(savedCustomer.getEmail());
        customerCredentialDto.setPassword(customerDto.getPassword());
        customerCredentialDto.setAdmin(savedCustomer.isAdmin());
        authService.saveCredential(customerCredentialDto);

    }

    private CustomerDto mapCustomerToDto(Customer cust) {
        var customerDto = modelMapper.map(cust, CustomerDto.class);
//        customerDto.setBillingAddress(modelMapper.map(cust.getBillingAddress(), Add.class));
        //customerDto.setUser(modelMapper.map(cust.getUser(), UserDto.class));
        return customerDto;
    }

    private Customer mapDtoToCustomer(CustomerDto dto) {
        var customer = modelMapper.map(dto, Customer.class);
//        customer.setAddress(modelMapper.map(dto.getAddress(), Address.class));
        /*if(dto.getUser() != null)
        userRepo.findById(dto.getUser().getId())
                .ifPresent(customer::setUser);*/
        return customer;
    }

}
