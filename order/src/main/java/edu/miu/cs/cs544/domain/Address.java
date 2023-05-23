package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Address")
public class Address extends BaseEntity {
    @Id
    private Integer id;

    private String street;

    private String city;

    private String state;

    private String zip;

    private Boolean isDefault;

    @Enumerated(EnumType.STRING)
    private AddressType type;
//    @ManyToOne
//    @JoinColumn(name = "stateProvinceId")
//    private StateProvince stateProvince;

}
