package edu.miu.cs.controllers;

import edu.miu.cs.dto.ProductDTO;
import edu.miu.cs.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping
    public Page<ProductDTO> getAll(Pageable pageable) {
        return productService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
       var res = productService.getById(id);
       return new ResponseEntity<ProductDTO>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ProductDTO productDTO){
        var res = productService.add(productDTO);
        return new ResponseEntity<ProductDTO>(res, HttpStatus.OK);
    }

    @PostMapping("/{id}/productItem")
    public ResponseEntity<?> addProductItem(@PathVariable Integer id, @RequestBody ProductDTO productDTO){
        ProductDTO res = productService.addProductItem(id, productDTO);
        return new ResponseEntity<ProductDTO>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        boolean res = productService.delete(id);
        return new ResponseEntity<Boolean>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO){
        boolean res = productService.update(id, productDTO);
        return new ResponseEntity<Boolean>(res, HttpStatus.OK);
    }
}
