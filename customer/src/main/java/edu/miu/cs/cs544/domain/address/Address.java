package edu.miu.cs.cs544.domain.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.miu.cs.cs544.domain.Audit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String street;
    @NotEmpty
    private String city;
    @NotEmpty
    private String state;
    @NotEmpty
    private String zip;

    @JsonProperty
    private boolean isDefault;
    @Enumerated(EnumType.STRING)
    AddressType type;

    @Embedded
    private Audit audit;
}
