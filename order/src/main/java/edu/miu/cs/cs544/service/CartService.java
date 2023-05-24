package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.contract.CartDto;
import edu.miu.cs.cs544.contract.OrderDto;

public interface CartService {
    CartDto getCart(Integer cartId) throws Exception;

    CartDto createNewCart(Integer customerId);

    CartDto addProductToCart(Integer cartId, Integer productId, Integer quantity, Double discountValue);

    CartDto updateCartLineItem(Integer cartId, Integer cartLineItemId, Integer quantity);

    CartDto deleteCartLineItem(Integer cartId, Integer cartEntryId);

    CartDto setShippingAddress(Integer cartId, Integer shippingAddressId);

    CartDto setCreditCard(Integer cartId, Integer creditCardId);

    OrderDto placeOrder(Integer cartId) throws Exception;
}
