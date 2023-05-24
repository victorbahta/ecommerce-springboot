package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Order;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.hamcrest.Matchers.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class OrderRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;



    @Test
    public void getOneOrderTest() {

        // given
        int id = 102;
        Order order = new Order();
        order.setId(id);
        order.setTotal(7056.0);
        entityManager.merge(order);

        // when
        Order found = orderRepository.findById(id).get();

        // then
       assertThat(order.getId())
          .isEqualTo(found.getId());
        // and
        assertThat(order.getTotal())
        .isEqualTo(found.getTotal());
    }


}