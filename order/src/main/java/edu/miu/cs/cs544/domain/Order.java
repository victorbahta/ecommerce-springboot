package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "orderId")
    private List<OrderLineItem> lineItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDate orderDate;

    private LocalDate deliveryDate;

    private Double subTotal;

    private Double taxAmount;

    private Double shippingCost;

    private Double total;

    @ManyToOne
    @JoinColumn(name = "shippingAddressId")
    private Address shippingAddress;

    @ManyToOne
    @JoinColumn(name = "creditCardId")
    private CreditCard creditCard;

    private Integer customerId;

}
