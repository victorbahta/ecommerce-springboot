package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "OrderLineItem")
public class OrderLineItem extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private Integer quantity;

    private Double discountValue;
}
