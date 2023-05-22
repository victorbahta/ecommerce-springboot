package edu.miu.cs.dto;

import edu.miu.cs.domains.Review;
import lombok.Data;
import lombok.ToString;

import java.util.Collection;
import java.util.PrimitiveIterator;

@Data
@ToString
public class ProductDTO {
    private int id;
    private String productId;
    private String name;
    private String description;
    private double price;
    private String image;
    private String barcodeNumber;
    private int stock;
    private ProductType productType;
    private Collection<ReviewDTO> reviews;
    private Collection<ProductDTO> products;
}
