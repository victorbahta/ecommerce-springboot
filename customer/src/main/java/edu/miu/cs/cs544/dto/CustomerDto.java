package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.CreditCard;
import edu.miu.cs.cs544.domain.address.Address;
import edu.miu.cs.cs544.domain.address.BillingAddress;
import lombok.Data;

import java.util.Collection;

@Data
public class CustomerDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private BillingAddress billingAddress;
    private Collection<Address> shippingAddress;
    private Collection<CreditCard> creditCards;
}
