package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.contract.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping(value = "/{productId}")
    Optional<ProductDto> findProductById(@PathVariable("productId") Integer productId) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productId);
        productDto.setName("Macbook");
        productDto.setPrice(2100.0);
        return Optional.of(productDto);
    }
}
