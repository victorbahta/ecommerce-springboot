package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.CartLineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartLineItemRepository extends JpaRepository<CartLineItem, Integer> {
}
