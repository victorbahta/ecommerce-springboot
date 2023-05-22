package edu.miu.cs.cs544.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity

public class CreditCard {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String creditNumber;

    private LocalDate expirationDate;

    private String securityCode;

//    @Embedded
//    private Audit audit;

}
