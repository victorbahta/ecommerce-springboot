package edu.miu.cs.cs544.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CountryRegion")
public class CountryRegion extends BaseEntity {

    @Id
    private String code;

    private String name;

}
