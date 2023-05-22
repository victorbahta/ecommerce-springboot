package edu.miu.cs.domains;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue
    private int id;

    private String productId;
    private String name;
    private String description;
    private double price;
    @Lob
    private String image;
    private String barcodeNumber;
    private int stock;
    @OneToMany
    private Collection<Review> reviews;
}
