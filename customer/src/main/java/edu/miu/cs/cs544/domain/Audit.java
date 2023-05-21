package edu.miu.cs.cs544.domain;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class Audit {
    Integer createdBy;
    Integer updatedBy;
    LocalDateTime createdOn;
    LocalDateTime updatedOn;
}
