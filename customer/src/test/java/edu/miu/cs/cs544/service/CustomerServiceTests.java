package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.dto.CustomerCredentialDto;
import edu.miu.cs.cs544.dto.CustomerDto;
import edu.miu.cs.cs544.feign.AuthService;
import edu.miu.cs.cs544.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerServiceTests {

    @TestConfiguration
    static class CustomerServiceImplTestContextConfiguration {
        @Bean
        public CustomerService customerService() {
            return new CustomerServiceImpl();
        }

        @Bean
        ModelMapper getModelMapper() {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper;
        }



    }


//    @Autowired
//    private CustomerService customerService;

    @MockBean
    AuthService authService;

    @MockBean
    private CustomerRepository customerRepository;
 
    @Before
    public void setUp() {
    	Customer customer = new Customer();
        customer.setId(999);
        customer.setFirstName("Mari");
        customer.setLastName("land");
        customer.setEmail("test1@gmail.com");
        Optional<Customer> accountOptional= Optional.of(customer);
        Mockito.when(customerRepository.findById(999))
          .thenReturn(accountOptional);
    }
    
    @Test
    public void whenValidCustomerNumberThenCustomerShouldBeFound() {
        CustomerDto found = new CustomerServiceImplTestContextConfiguration().customerService().findById(999);
      
         assertThat(found.getId())
          .isEqualTo(999);
         assertThat(found.getFirstName())
         .isEqualTo("Mari");
     }
}