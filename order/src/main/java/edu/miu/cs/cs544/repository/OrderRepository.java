package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
