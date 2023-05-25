package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.dto.UserDTO;
import edu.miu.cs.cs544.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceTest {



    @TestConfiguration
    static class UserServiceImplTestContextConfiguration{
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public UserService userService() {
            return new UserService();
        }
    }
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;


    @Before
    public void setUp(){
        String email = "test@email.com";
        User user = new User(999, email, "123", true);
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
    }

    @Test
    public void whenValidUserIdThenUserShouldBeFound(){
        String email = "test@email.com";
        UserDTO userDTO = userService.findByEmail(email).orElseThrow();
        assertThat(userDTO.getEmail()).isEqualTo(email);
    }
}
