package edu.miu.cs.cs544.contract;

import lombok.Data;

@Data
public class CustomerDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isAdmin;
//    private Address billingAddress;
//    private Collection<Address> shippingAddress;
//    private Collection<CreditCard> creditCards;
}
