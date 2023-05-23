package edu.miu.cs.cs544.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.miu.cs.cs544.domain.address.AddressType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ShippingAddressDto {

    private Integer id;
    private String street;
    private String city;
    private String state;
    private String zip;
    private boolean isDefault;
    AddressType type;
}
