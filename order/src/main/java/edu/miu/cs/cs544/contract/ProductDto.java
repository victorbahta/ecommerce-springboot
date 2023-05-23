package edu.miu.cs.cs544.contract;

import lombok.Data;

@Data
public class ProductDto {
    private Integer id;
    private String name;

    private Double price;
}
