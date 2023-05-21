package edu.miu.cs.cs544.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CreditCart")
public class CreditCard extends BaseEntity {

    @Id
    private String number;

    private Integer expiredMonth;
    private Integer expiredYear;

    @Column(length = 3)
    private String securityCode;
}
