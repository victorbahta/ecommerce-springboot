package edu.miu.cs.cs544.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Date;

@Embeddable
@Data
public class Audit {
    private Date modifiedDate = new Date();
}
