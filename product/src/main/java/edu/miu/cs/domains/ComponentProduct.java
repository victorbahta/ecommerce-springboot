package edu.miu.cs.domains;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
@Table(name = "Product")
@DiscriminatorColumn(
        name = "productType",
        discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ComponentProduct {
    @Id
    @GeneratedValue
    private int id;

    private String productId;
    private String name;
    private String description;
    private double price;
    private double discountAmount;
    @Lob
    private String image;
    private String barcodeNumber;
    private int stock;

//    public boolean isSingleProduct() {
//        if (this.productItems == null || this.productItems.size() == 0) {
//            return true;
//        }
//        return false;
//    }

    @OneToMany
    @JoinColumn(name = "productId")
    private Collection<Review> reviews;
}
