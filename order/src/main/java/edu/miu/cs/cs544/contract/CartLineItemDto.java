package edu.miu.cs.cs544.contract;

import lombok.Data;

@Data
public class CartLineItemDto {
    private Integer id;

    private ProductDto product;

    private Integer quantity;

    private Double discountValue;
}
