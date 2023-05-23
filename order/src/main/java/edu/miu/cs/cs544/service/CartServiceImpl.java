package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.contract.*;
import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.feign.CustomerServiceFeignClient;
import edu.miu.cs.cs544.feign.ProductServiceFeignClient;
import edu.miu.cs.cs544.repository.CartLineItemRepository;
import edu.miu.cs.cs544.repository.CartRepository;
import edu.miu.cs.cs544.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private static final Logger LOG = LoggerFactory.getLogger(CartServiceImpl.class);
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartLineItemRepository cartLineItemRepository;
    @Autowired
    private Converter<Cart, CartDto> cartConverter;
    @Autowired
    private Converter<Product, ProductDto> productConverter;
    @Autowired
    private Converter<CreditCard, CreditCardDto> creditCardConverter;
    @Autowired
    private Converter<Address, AddressDto> addressConverter;
    @Autowired
    private Converter<Order, OrderDto> orderConverter;
    @Autowired
    private CustomerServiceFeignClient customerServiceFeignClient;
    @Autowired
    private ProductServiceFeignClient productServiceFeignClient;
    @Autowired
    private ShippingCostStrategy shippingCostStrategy;
    @Autowired
    private TaxCalculationStrategy taxCalculationStrategy;

    @Override
    public CartDto getCart(Integer cartId) {
        return cartRepository.findById(cartId).map(c -> {
            CartDto cartDto = cartConverter.toDto(c);
            try {
                customerServiceFeignClient.findCustomerById(c.getCustomerId()).ifPresent(cartDto::setCustomer);
            } catch(Exception ex) {
                LOG.error("Cannot call customer service api to get customer for id {}", c.getCustomerId(), ex);
            }
            return cartDto;
        }).orElse(null);
    }

    @Override
    public CartDto createNewCart(Integer customerId) {
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        Cart savedCart = cartRepository.save(cart);
        CartDto cartDto = cartConverter.toDto(savedCart);
        try {
            customerServiceFeignClient.findCustomerById(customerId).ifPresent(cartDto::setCustomer);
        } catch (Exception ex) {
            LOG.error("Cannot call customer service api to get customer for id {}", customerId, ex);
        }
        return cartDto;
    }

    @Override
    public CartDto addProductToCart(Integer cartId, Integer productId, Integer quantity, Double discountValue) {
        cartRepository.findById(cartId).map(cart -> {
            CartLineItem item = new CartLineItem();
            try {
                productServiceFeignClient.findProductById(productId).ifPresent(p -> {
                    item.setProduct(productConverter.fromDto(p));
                });
            } catch(Exception ex) {
                LOG.error("Cannot call product service api to get product for id {}", productId, ex);
            }
            item.setQuantity(quantity);
            item.setDiscountValue(discountValue);
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
            try {
                customerServiceFeignClient.findAddressById(cart.getCustomerId(), shippingAddressId).ifPresent(addr -> {
                    addr.setType(AddressType.SHIPPING);
                    cart.setShippingAddress(addressConverter.fromDto(addr));
                });
            } catch(Exception ex) {
                LOG.error("Cannot call customer service api to get shipping address for id {}", shippingAddressId, ex);
            }
            return cartRepository.save(cart);
        }).map(cartConverter::toDto).orElse(null);
    }

    @Override
    public CartDto setCreditCard(Integer cartId, Integer creditCardId) {
        return cartRepository.findById(cartId).map(cart -> {
            try {
                customerServiceFeignClient.findCreditCardById(cart.getCustomerId(), creditCardId).ifPresent(cc -> {
                    cart.setCreditCard(creditCardConverter.fromDto(cc));
                });
            } catch(Exception ex) {
                LOG.error("Cannot call customer service api to get credit card for id {}", creditCardId, ex);
            }
            return cartRepository.save(cart);
        }).map(cartConverter::toDto).orElse(null);
    }

    @Override
    public OrderDto placeOrder(Integer cartId) {
        return cartRepository.findById(cartId).map(cart-> {
            calculateCart(cart);
            Order order = copyCartToOrder(cart);
            cartRepository.delete(cart);
            return orderRepository.save(order);
        }).map(orderConverter::toDto).orElse(null);
    }

    private void calculateCart(Cart cart) {
        Double subTotal = cart.getLineItems().stream().filter(i -> i.getProduct() != null).map(i -> i.getProduct().getPrice() * i.getQuantity()).reduce(0.0, Double::sum);
        cart.setSubTotal(subTotal);
        Double shippingCost = shippingCostStrategy.calculateShippingCost(cart);
        Double taxAmount = taxCalculationStrategy.calculateTax(cart);
        cart.setShippingCost(shippingCost);
        cart.setTaxAmount(taxAmount);
        cart.setTotal(subTotal + shippingCost + taxAmount);
    }

    private Order copyCartToOrder(Cart cart) {
//        return modelMapper.map(cart, Order.class);
        Order order = new Order();
        order.setCustomerId(cart.getCustomerId());
        order.setShippingAddress(cart.getShippingAddress());
        order.setCreditCard(cart.getCreditCard());
        order.setSubTotal(cart.getSubTotal());
        order.setShippingCost(cart.getShippingCost());
        order.setTaxAmount(cart.getTaxAmount());
        order.setTotal(cart.getTotal());
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.Placed);
        order.setLineItems(cart.getLineItems().stream().map(i -> {
            OrderLineItem lineItem = new OrderLineItem();
            lineItem.setProduct(i.getProduct());
            lineItem.setQuantity(i.getQuantity());
            lineItem.setDiscountValue(i.getDiscountValue());
            return lineItem;
        }).collect(Collectors.toList()));
        return order;
    }
}
