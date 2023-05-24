package edu.miu.cs.domains;

import jakarta.persistence.*;
import lombok.Data;
//import lombok.Data;

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
    private boolean isActive;

    @OneToMany(mappedBy = "product")
    private Collection<Review> reviews;

    @Override
    public String toString() {
        return "ComponentProduct{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discountAmount=" + discountAmount +
                ", image='" + image + '\'' +
                ", barcodeNumber='" + barcodeNumber + '\'' +
                ", stock=" + stock +
                ", isActive=" + isActive +
                '}';
    }
}
