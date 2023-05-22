package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.dto.AuthenticationRequest;
import edu.miu.cs.cs544.dto.AuthenticationResponse;
import edu.miu.cs.cs544.service.AuthenticationService;
import edu.miu.cs.cs544.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private UserService userService;
    private final AuthenticationService authenticationService;
    public LoginController(UserService userService, AuthenticationService authenticationService){
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }
}
