package edu.miu.cs.domains;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String description;
    private int numberOfStar;
    private Date reviewDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private ComponentProduct product;
    @Embedded
    private Reviewer reviewer;
}
