package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "StateProvince")
public class StateProvince extends BaseEntity {

    @Id
    private Integer id;

    private String code;

    private String name;

    @ManyToOne
    @JoinColumn(name = "countryRegionCode")
    private CountryRegion countryRegion;

}
