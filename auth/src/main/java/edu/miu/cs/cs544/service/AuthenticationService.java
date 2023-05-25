package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.dto.AuthenticationRequest;
import edu.miu.cs.cs544.dto.AuthenticationResponse;
import edu.miu.cs.cs544.dto.CustomErrorType;
import edu.miu.cs.cs544.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder){

        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> authenticate(AuthenticationRequest request) {

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        //verify user
        if(optionalUser.isPresent()){
            String hash_password = optionalUser.get().getPassword();
            if(passwordEncoder.matches(request.getPassword(), hash_password)) {
                AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtService.generateToken(optionalUser.get()), optionalUser.get().getId());
                return new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);
            }else{
                return new ResponseEntity<CustomErrorType>(new CustomErrorType("UNAUTHORIZED"), HttpStatus.UNAUTHORIZED);
            }
        }else{
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("UNAUTHORIZED"), HttpStatus.UNAUTHORIZED);
        }



    }
}
