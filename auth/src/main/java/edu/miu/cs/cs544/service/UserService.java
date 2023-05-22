package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void saveUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

}
