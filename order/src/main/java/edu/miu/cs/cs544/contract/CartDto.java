package edu.miu.cs.cs544.contract;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private Integer id;

    private Integer customerId;

    private List<CartLineItemDto> lineItems;

    private Integer shippingAddressId;
    private Integer creditCardId;
}
