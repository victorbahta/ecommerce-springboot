package edu.miu.cs.cs544.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    private Integer id;
    private String name;
    private Double price;
}
