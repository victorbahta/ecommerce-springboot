package edu.miu.cs.cs544.contract;

import edu.miu.cs.cs544.domain.AddressType;
import edu.miu.cs.cs544.domain.StateProvince;
import lombok.Data;

@Data
public class AddressDto {
    private Integer id;

    private String line1;

    private String line2;

    private String city;

    private StateProvince stateProvince;

    private String postalCode;

    private AddressType addressType;
}
