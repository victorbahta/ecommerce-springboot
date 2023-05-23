package edu.miu.cs.cs544.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.miu.cs.cs544.domain.address.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.util.Collection;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column( nullable = false)
    private String firstName;

    @Column( nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    @NotEmpty
    @Email
    private String email;

    @JsonProperty
    private boolean isAdmin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billingAddressId")
    private Address billingAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private Collection<Address> shippingAddress;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name="customerId")
    private Collection<CreditCard> creditCards;

//    @Embedded
//    private Audit audit;

}
