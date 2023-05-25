package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.contract.*;
import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.feign.CustomerServiceFeignClient;
import edu.miu.cs.cs544.feign.ProductServiceFeignClient;
import edu.miu.cs.cs544.repository.CartLineItemRepository;
import edu.miu.cs.cs544.repository.CartRepository;
import edu.miu.cs.cs544.repository.OrderRepository;
import edu.miu.cs.cs544.service.converter.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CartServiceImplTest {

    private Integer cartId = 1;
    private Integer customerId = 1;
    private Integer productId = 1;
    private Integer quantity = 1;
    private Double discountValue = 0.0;
    private Integer cartLineItemId = 1;
    private Integer shippingAddressId = 1;
    private Integer creditCardId = 1;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private CartLineItemRepository cartLineItemRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CartConverter cartConverter;
    @Mock
    private OrderConverter orderConverter;
    @Mock
    private ProductConverter productConverter;

    @Mock
    private AddressConverter addressConverter;

    @Mock
    private CreditCardConverter creditCardConverter;

    @Mock
    private CustomerServiceFeignClient customerServiceFeignClient;

    @Mock
    private ProductServiceFeignClient productServiceFeignClient;

    @Mock
    private ShippingCostStrategy shippingCostStrategy;

    @Mock
    private TaxCalculationStrategy taxCalculationStrategy;
    @Mock
    private ModelMapper modelMapper;

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
        when(customerServiceFeignClient.findCustomerById(any())).thenReturn(Optional.of(customer));
        CartDto actualResult = cartService.createNewCart(customerId);
        assertEquals(cartDto, actualResult);
    }

    @Test
    public void addProductToCart() {
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        cart.setId(cartId);
        CartLineItem lineItem = new CartLineItem();
        ProductDto product = new ProductDto();
        product.setId(1);
        product.setName("Product 1");
        product.setPrice(100.0);

        CartDto cartDto = new CartDto();
        CustomerDto customer = new CustomerDto();
        cartDto.setId(cartId);
        customer.setId(customerId);
        cartDto.setCustomer(customer);
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(cartRepository.save(any())).thenReturn(cart);
        when(cartConverter.toDto(any())).thenReturn(cartDto);
        when(productServiceFeignClient.findProductById(any())).thenReturn(Optional.of(product));
        when(productConverter.fromDto(product)).thenReturn(any());
        CartDto actualResult = cartService.addProductToCart(cartId, productId, quantity, discountValue);
        assertEquals(cartDto, actualResult);
    }

    @Test
    public void updateCartLineItem() {
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        cart.setId(cartId);
        CartLineItem lineItem = new CartLineItem();
        Product product = new Product();
        product.setId(1);
        product.setName("Product 1");
        product.setPrice(100.0);
        lineItem.setProduct(product);
        lineItem.setQuantity(quantity);
        lineItem.setDiscountValue(discountValue);
        lineItem.setId(1);
        cart.setLineItems(List.of(lineItem));
        CartDto cartDto = new CartDto();
        CustomerDto customer = new CustomerDto();
        cartDto.setId(cartId);
        customer.setId(customerId);
        cartDto.setCustomer(customer);
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(cartConverter.toDto(any())).thenReturn(cartDto);
        CartDto actualResult = cartService.updateCartLineItem(cartId, cartLineItemId, quantity);
        assertEquals(cartDto, actualResult);
    }

    @Test
    public void setShippingAddress() {
        Address address = new Address();
        address.setId(1);
        AddressDto addressDto = new AddressDto();
        addressDto.setType(AddressType.SHIPPING);
        addressDto.setId(1);
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        cart.setId(cartId);
        CartDto cartDto = new CartDto();
        CustomerDto customer = new CustomerDto();
        cartDto.setId(cartId);
        customer.setId(customerId);
        cartDto.setCustomer(customer);
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(cartRepository.save(any())).thenReturn(cart);
        when(cartConverter.toDto(any())).thenReturn(cartDto);
        when(customerServiceFeignClient.findAddressById(customerId, shippingAddressId)).thenReturn(Optional.of(addressDto));
        when(addressConverter.fromDto(addressDto)).thenReturn(address);
        CartDto actualResult = cartService.setShippingAddress(cartId, shippingAddressId);
        assertEquals(cartDto, actualResult);
        assertEquals(cartDto.getShippingAddress(), actualResult.getShippingAddress());
    }

    @Test
    public void setCreditCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(1);
        CreditCardDto creditCardDto = new CreditCardDto();
        creditCardDto.setId(1);
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        cart.setId(cartId);
        CartDto cartDto = new CartDto();
        CustomerDto customer = new CustomerDto();
        cartDto.setId(cartId);
        customer.setId(customerId);
        cartDto.setCustomer(customer);
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(cartRepository.save(any())).thenReturn(cart);
        when(cartConverter.toDto(any())).thenReturn(cartDto);
        when(customerServiceFeignClient.findCreditCardById(customerId, creditCardId)).thenReturn(Optional.of(creditCardDto));
        when(creditCardConverter.fromDto(creditCardDto)).thenReturn(creditCard);
        CartDto actualResult = cartService.setCreditCard(cartId, creditCardId);
        assertEquals(cartDto, actualResult);
        assertEquals(cartDto.getCreditCard(), actualResult.getCreditCard());
    }

    @Test
    public void placeOrder() throws Exception {
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        cart.setId(cartId);
        CartLineItem lineItem = new CartLineItem();
        Product product = new Product();
        product.setId(1);
        product.setName("Product 1");
        product.setPrice(100.0);
        lineItem.setProduct(product);
        lineItem.setQuantity(quantity);
        lineItem.setDiscountValue(discountValue);
        lineItem.setId(1);
        cart.setLineItems(List.of(lineItem));
        CartDto cartDto = new CartDto();
        CustomerDto customer = new CustomerDto();
        cartDto.setId(cartId);
        customer.setId(customerId);
        cartDto.setCustomer(customer);
        Order order = new Order();
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1);
        orderDto.setCustomer(customer);
        orderDto.setLineItems(List.of(new OrderLineItemDto()));
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(orderRepository.save(any())).thenReturn(order);
        when(modelMapper.map(cart, Order.class)).thenReturn(order);
        when(orderConverter.toDto(order)).thenReturn(orderDto);
        when(customerServiceFeignClient.findCustomerById(any())).thenReturn(Optional.of(customer));
        OrderDto actualResult = cartService.placeOrder(cartId);
        assertEquals(1, actualResult.getLineItems().size());
    }
}