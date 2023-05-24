package edu.miu.cs.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ReviewDTO {
    private int id;

    private String title;
    private String description;
    private int numberOfStar;
    private Date reviewDate;
    private int reviewerId;
    private int orderId;
    private String reviewerFirstname;
    private String reviewerLastname;
}
