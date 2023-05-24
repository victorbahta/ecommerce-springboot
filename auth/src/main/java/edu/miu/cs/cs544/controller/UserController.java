package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.dto.CustomErrorType;
import edu.miu.cs.cs544.dto.UserDTO;
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
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO){
        Optional<UserDTO> optUserDTO = userService.findByEmail(userDTO.getEmail());
        if(optUserDTO.isPresent()){
            UserDTO userInDB = optUserDTO.get();
            if(!userInDB.getId().equals(userDTO.getId())) {
                return new ResponseEntity<CustomErrorType>(new CustomErrorType("Email " + userDTO.getEmail() + " already exist with different Id"), HttpStatus.BAD_REQUEST);
            }
        }

        userService.saveUser(userDTO);
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
