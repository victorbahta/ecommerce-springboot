package edu.miu.cs.cs544.contract;

import lombok.Data;

@Data
public class CartLineItemDto {
    private Integer id;

    private Integer productId;

    private Integer quantity;

    private Double discountValue;
}
