package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
