package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.contract.CartDto;
import edu.miu.cs.cs544.feign.CustomerServiceFeignClient;
import edu.miu.cs.cs544.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@Tag(name = "Reports API", description = "Cart Controller")
public class CartController {

    @Autowired
    private CartService cartService;

    @Operation(summary = "Get a cart")
    @GetMapping("/{cartId}")
    public CartDto getCart(@PathVariable("cartId") Integer cartId) {

        return cartService.getCart(cartId);

    }

    @Operation(summary = "Create a new cart")
    @PostMapping
    public CartDto createNewCart(@RequestHeader("customerId") Integer customerId) {

        return cartService.createNewCart(customerId);

    }

    @Operation(summary = "Add a product to cart")
    @PostMapping("/{cartId}/entries")
    public CartDto addProductToCart(@PathVariable("cartId") Integer cartId,
                                    @RequestParam("productId") Integer productId,
                                    @RequestParam("quantity") Integer quantity,
                                    @RequestParam(name = "discountValue", required = false) Double discountValue) {

        return cartService.addProductToCart(cartId, productId, quantity, discountValue);
    }

    @Operation(summary = "Update quantity of an line item in cart")
    @PutMapping("/{cartId}/entries")
    public CartDto updateCartLineItem(@PathVariable("cartId") Integer cartId,
                                      @RequestParam("cartEntryId") Integer cartEntryId,
                                      @RequestParam("quantity") Integer quantity) {

        return cartService.updateCartLineItem(cartId, cartEntryId, quantity);
    }

    @Operation(summary = "Delete an line item in cart")
    @DeleteMapping("/{cartId}/entries")
    public CartDto deleteCartLineItem(@PathVariable("cartId") Integer cartId,
                                      @RequestParam("cartEntryId") Integer cartEntryId) {

        return cartService.deleteCartLineItem(cartId, cartEntryId);
    }

    @Operation(summary = "Set shipping address in cart")
    @PostMapping("/{cartId}/shippingaddress")
    public CartDto selectShippingAddress(@PathVariable("cartId") Integer cartId,
                                         @RequestParam("shippingAddressId") Integer shippingAddressId) {

        return cartService.setShippingAddress(cartId, shippingAddressId);
    }

    @Operation(summary = "Set credit card in cart")
    @PostMapping("/{cartId}/creditcard")
    public CartDto setCreditCard(@PathVariable("cartId") Integer cartId,
                                 @RequestParam("creditCardId") Integer creditCardId) {

        return cartService.setCreditCard(cartId, creditCardId);
    }

}
