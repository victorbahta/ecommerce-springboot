package edu.miu.cs.cs544.contract;

import edu.miu.cs.cs544.domain.AddressType;
import lombok.Data;

@Data
public class AddressDto {
    private Integer id;

    private String street;

    private String city;

    private String state;

    private String zip;

    private AddressType type;
}
