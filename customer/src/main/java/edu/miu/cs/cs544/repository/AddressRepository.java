package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.CreditCard;
import edu.miu.cs.cs544.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("SELECT sa FROM  Customer c JOIN c.shippingAddress sa WHERE c.id = :customerId AND  sa.id = :shippingId")
    public Address findByShippingId(Integer customerId, Integer shippingId);

}
