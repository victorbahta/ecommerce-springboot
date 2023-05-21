package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.customerId=:customerId")
    Page<Order> findByCustomerId(Integer customerId, Pageable pageable);
}
