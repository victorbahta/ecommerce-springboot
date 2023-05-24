package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.contract.CartDto;
import edu.miu.cs.cs544.contract.CustomerDto;
import edu.miu.cs.cs544.domain.Cart;
import edu.miu.cs.cs544.repository.CartRepository;
import edu.miu.cs.cs544.service.converter.CartConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CartServiceImplTest {

    private Integer cartId = 1;
    private Integer customerId = 1;
    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartConverter cartConverter;

    @InjectMocks
    private CartServiceImpl cartService;

    @Test
    public void getCart() {
    }

    @Test
    public void createNewCart() {
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        cart.setId(cartId);
        CartDto cartDto = new CartDto();
        CustomerDto customer = new CustomerDto();
        cartDto.setId(cartId);
        customer.setId(customerId);
        cartDto.setCustomer(customer);
        when(cartRepository.save(any())).thenReturn(cart);
        when(cartConverter.toDto(any())).thenReturn(cartDto);
        CartDto actualResult = cartService.createNewCart(customerId);
        assertEquals(cartDto, actualResult);
    }

    @Test
    public void addProductToCart() {

    }

    @Test
    public void updateCartLineItem() {
    }

    @Test
    public void deleteCartLineItem() {
    }

    @Test
    public void setShippingAddress() {
    }

    @Test
    public void setCreditCard() {
    }

    @Test
    public void placeOrder() {
    }
}