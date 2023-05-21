package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.dto.CustomerDto;
import edu.miu.cs.cs544.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ModelMapper modelMapper;

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

        var customer = mapDtoToCustomer(customerDto);
        customerRepo.save(customer);
        return true;
    }

    @Override
    public boolean deleteById(Integer id) {
        customerRepo.deleteById(id);
        return true;
    }


    private CustomerDto mapCustomerToDto(Customer cust) {
        var customerDto = modelMapper.map(cust, CustomerDto.class);
//        customerDto.setAddress(modelMapper.map(cust.getAddress(), AddressDto.class));
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
