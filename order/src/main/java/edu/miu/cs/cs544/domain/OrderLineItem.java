package edu.miu.cs.cs544.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "OrderLineItem")
public class OrderLineItem extends BaseEntity {

    @Id
    private String id;

    private String productId;

    private Integer quantity;

    private Double discountValue;

    @Embedded
    private Audit audit;
}
