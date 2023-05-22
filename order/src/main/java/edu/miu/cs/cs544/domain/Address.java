package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Address")
public class Address extends BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private String line1;

    private String line2;

    private String city;

    @ManyToOne
    @JoinColumn(name = "stateProvinceId")
    private StateProvince stateProvince;

    private String postalCode;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;
}
