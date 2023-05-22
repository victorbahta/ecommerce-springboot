package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.contract.AddressDto;
import edu.miu.cs.cs544.contract.CartDto;
import edu.miu.cs.cs544.contract.CreditCardDto;
import edu.miu.cs.cs544.contract.OrderDto;
import edu.miu.cs.cs544.domain.Address;
import edu.miu.cs.cs544.domain.Cart;
import edu.miu.cs.cs544.domain.CartLineItem;
import edu.miu.cs.cs544.domain.CreditCard;
import edu.miu.cs.cs544.repository.CartLineItemRepository;
import edu.miu.cs.cs544.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartLineItemRepository cartLineItemRepository;
    @Autowired
    private Converter<Cart, CartDto> cartConverter;
    @Autowired
    private Converter<Address, AddressDto> addressConverter;
    @Autowired
    private Converter<CreditCard, CreditCardDto> creditCardConverter;

    @Override
    public CartDto getCart(Integer cartId) {
        return cartRepository.findById(cartId).map(cartConverter::toDto).orElse(null);
    }

    @Override
    public CartDto createNewCart(Integer customerId) {
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        Cart savedCart = cartRepository.save(cart);
        return cartConverter.toDto(savedCart);
    }

    @Override
    public CartDto addProductToCart(Integer cartId, Integer productId, Integer quantity) {
        cartRepository.findById(cartId).map(cart -> {
            CartLineItem item = new CartLineItem();
            item.setProductId(productId);
            item.setQuantity(quantity);
            item.setDiscountValue(0.00);
            cart.getLineItems().add(item);
            return cartRepository.save(cart);
        });

        return cartRepository.findById(cartId).map(cartConverter::toDto).orElse(null);
    }

    @Override
    public CartDto updateCartLineItem(Integer cartId, Integer cartLineItemId, Integer quantity) {
        cartRepository.findById(cartId)
                .flatMap(cart -> cart.getLineItems().stream().filter(line -> line.getId().equals(cartLineItemId)).findFirst())
                .ifPresent(lineItem -> {
                    lineItem.setQuantity(quantity);
                    cartLineItemRepository.save(lineItem);
                });
        return cartRepository.findById(cartId).map(cartConverter::toDto).orElse(null);
    }

    @Override
    public CartDto deleteCartLineItem(Integer cartId, Integer cartLineItemId) {
        cartRepository.findById(cartId)
                .flatMap(cart -> cart.getLineItems().stream().filter(line -> line.getId().equals(cartLineItemId)).findFirst())
                .ifPresent(lineItem -> {
                    cartLineItemRepository.delete(lineItem);
                });

//        cartRepository.findById(cartId).map(cart -> {
//            cart.getLineItems().
//        })
        return cartRepository.findById(cartId).map(cartConverter::toDto).orElse(null);
    }

    @Override
    public CartDto setShippingAddress(Integer cartId, Integer shippingAddressId) {
        return cartRepository.findById(cartId).map(cart -> {
            cart.setShippingAddressId(shippingAddressId);
            return cartRepository.save(cart);
        }).map(cartConverter::toDto).orElse(null);
    }

    @Override
    public CartDto setCreditCard(Integer cartId, Integer creditCardId) {
        return cartRepository.findById(cartId).map(cart -> {
            cart.setCreditCardId(creditCardId);
            return cartRepository.save(cart);
        }).map(cartConverter::toDto).orElse(null);
    }

    @Override
    public OrderDto placeOrder(Integer cartId) {
        return null;
    }
}
