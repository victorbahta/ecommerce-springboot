package edu.miu.cs.cs544;

import edu.miu.cs.cs544.dto.CustomerDto;
import edu.miu.cs.cs544.service.CustomerService;
import edu.miu.cs.cs544.service.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootTest
class CustomerApplicationTests {


	@TestConfiguration
	static class CustomerServiceImplTestContextConfiguration {
		@Bean
		public CustomerService customerService() {
			return new CustomerServiceImpl();
		}
	}

	@Test
	void contextLoads() {
	}

}
