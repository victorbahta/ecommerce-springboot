package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.contract.CartResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartController {

    @PutMapping("/{cartId}/add/{productCode}/{quantity}")
    public CartResponse addProductToCart(@PathVariable("cartId") String cartId,
                                         @PathVariable("productCode") String productCode,
                                         @PathVariable("quantity") Integer quantity) {

        return null;

    }
}
