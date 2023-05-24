package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Customer;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTests {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private CustomerRepository customerRepository;

//    Customer customer;
//    @BeforeEach
//    void init() {
//        customer = new Customer();
//        customer.setFirstName("Mari");
//        customer.setLastName("land");
//        customer.setEmail("test1@gmail.com");
//        customerRepository.save(customer);
//        entityManager.flush();
//    }

    @Test
    public void shouldCreateCustomer() {
        // given
        Customer customer = new Customer();
        customer.setFirstName("Mari");
        customer.setLastName("land");
        customer.setEmail("test1@gmail.com");
        entityManager.persist(customer);
        entityManager.flush();

        // when
        Customer found = customerRepository.findByEmail(customer.getEmail());
     
        // then
        assertThat(customer.getId())
          .isEqualTo(found.getId());
        // and
        assertThat(customer.getEmail())
        .isEqualTo(found.getEmail());
        // and
        assertThat(customer.getFirstName())
                .isEqualTo(found.getFirstName());
    }

 
}