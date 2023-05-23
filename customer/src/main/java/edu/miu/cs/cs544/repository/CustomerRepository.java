package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public Customer findByEmail(String email);
}
