package edu.miu.cs.repositories;

import edu.miu.cs.domains.CompositeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompositeProductRepository extends JpaRepository<CompositeProduct, Integer> {
}
