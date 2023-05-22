package edu.miu.cs.domains;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    @Embedded
    private Reviewer reviewer;
}
