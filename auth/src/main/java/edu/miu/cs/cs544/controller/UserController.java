package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.dto.CustomErrorType;
import edu.miu.cs.cs544.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        Optional<User> optUser = userService.findByEmail(user.getEmail());
        if(optUser.isPresent()){
            User userInDB = optUser.get();
            if(!userInDB.getId().equals(user.getId())) {
                return new ResponseEntity<CustomErrorType>(new CustomErrorType("Email " + user.getEmail() + " already exist with different Id"), HttpStatus.BAD_REQUEST);
            }
        }

        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    // this api is using for testing only
    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    // this api is using for testing only
    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable("id") Integer id){
        userService.deleteUser(id);
    }
}
