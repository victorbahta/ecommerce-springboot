package edu.miu.cs.cs544.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "CreditCart")
public class CreditCard extends BaseEntity {

    @Id
    private Integer id;
    private String name;
    private String creditNumber;

    private LocalDate expirationDate;
    @Column(length = 3)
    private String securityCode;
}
