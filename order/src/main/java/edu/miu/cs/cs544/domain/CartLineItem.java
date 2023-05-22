package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CartLineItem")
public class CartLineItem extends BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer productId;

    private Integer quantity;

    private Double discountValue;

    @Embedded
    private Audit audit;
}
