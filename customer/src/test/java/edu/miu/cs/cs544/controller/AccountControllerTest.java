package edu.miu.cs.cs544.controller;


import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.dto.CustomerDto;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.service.CreditCardService;
import edu.miu.cs.cs544.service.CustomerService;
import edu.miu.cs.cs544.service.ShippingAddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class AccountControllerTest {

	@Autowired
	MockMvc mock;

	@MockBean
	CustomerService customerService;
	
	@MockBean
	CustomerRepository customerRepository;

	@MockBean
	CreditCardService creditCardService;

	@MockBean
	ShippingAddressService shippingAddressService;

	@Test
	public void testGetCustomer() throws Exception {
		CustomerDto customer = new CustomerDto();
		customer.setId(999);
		customer.setFirstName("Mari");
		customer.setLastName("land");
		customer.setEmail("test1@gmail.com");

		Mockito.when(customerService.findById(999)).thenReturn(customer);
		mock.perform(MockMvcRequestBuilders.get("/customers/999"))
		  .andExpect(status().isOk())
		  .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Mari"));

	}

}
