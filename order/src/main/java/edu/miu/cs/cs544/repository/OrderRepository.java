package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.customerId=:customerId")
    Page<Order> findByCustomerId(Integer customerId, Pageable pageable);

    @Query("select o from Order o join fetch o.lineItems i join i.product p where o.customerId = :customerId and o.id = :orderId and p.id = :productId ")
    Order findByCustomerAndContainsProduct(@Param("customerId") Integer customerId, @Param("orderId") Integer orderId, @Param("productId") Integer productId);
}
