package edu.miu.cs.cs544.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;

@Embeddable
@Data
public class Audit {
    private LocalDate modifiedDate;
}
