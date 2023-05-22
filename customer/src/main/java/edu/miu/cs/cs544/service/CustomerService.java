package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    List<CustomerDto> findAll();

    CustomerDto findById(Integer id);

    boolean addNewCustomer(CustomerDto customer);

    boolean updateCustomer(CustomerDto customerDto, Integer customerId);

    boolean deleteById(Integer id);

}
