package edu.miu.cs.cs544.controller;


import edu.miu.cs.cs544.dto.CustomerDto;
import edu.miu.cs.cs544.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    ResponseEntity<List<CustomerDto>> findAll() {

        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.findById(id));
    }


    @PostMapping()
    ResponseEntity<Boolean> addCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.addNewCustomer(customerDto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.deleteById(id));
    }




}
