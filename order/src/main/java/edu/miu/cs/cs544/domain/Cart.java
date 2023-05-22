package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Carts")
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer customerId;

    private Integer shippingAddressId;
    private Integer creditCardId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cartId")
    private List<CartLineItem> lineItems = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "shippingAddressId")
//    private Address shippingAddress;
//
//    @ManyToOne
//    @JoinColumn(name = "creditCardId")
//    private CreditCard creditCard;
}
