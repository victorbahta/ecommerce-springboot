package edu.miu.cs.cs544.contract;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private Integer id;

    private Integer customerId;
    private CustomerDto customer;

    private List<CartLineItemDto> lineItems;

    private AddressDto shippingAddress;
    private CreditCardDto creditCard;
}
