package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByEmail_thenReturnUser(){
        String email = "test@email.com";
        User user = new User(999, "test@email.com", "123", true);
        entityManager.persist(user);
        entityManager.flush();

        Optional<User> found = userRepository.findByEmail(email);
        assertEquals(user.getEmail(),found.orElseThrow().getEmail());
    }
}
