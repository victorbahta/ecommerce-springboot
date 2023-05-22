package edu.miu.cs.cs544.domain;

import edu.miu.cs.cs544.domain.address.Address;
import edu.miu.cs.cs544.domain.address.BillingAddress;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    @OneToOne
    @JoinColumn(name = "billingAddressId")
    private BillingAddress billingAddress;

    @OneToMany
    @JoinColumn(name = "customerId")
    private Collection<Address> shippingAddress;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="customerId")
    private Collection<CreditCard> creditCards;

    @Embedded
    private Audit audit;






}
