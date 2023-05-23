package edu.miu.cs.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.ToString;

@Embeddable
@Data
@ToString
public class Reviewer {
    @Column(name = "reviewerId")
    private int id;
    @Column(name = "reviewerFirstname")
    private String firstname;
    @Column(name = "reviewerLastname")
    private String lastname;
}
