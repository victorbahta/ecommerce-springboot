package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.dto.UserDTO;
import edu.miu.cs.cs544.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }
    public void saveUser(UserDTO userDto){
        User user =modelMapper.map(userDto,User.class);
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
