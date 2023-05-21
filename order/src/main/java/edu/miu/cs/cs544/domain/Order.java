package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "Orders")
public class Order extends BaseEntity {

    @Id
    private Integer id;

    @OneToMany
    @JoinColumn(name = "orderId")
    private List<OrderLineItem> lineItems;

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

    private Integer customerId;

}
