package edu.miu.cs.repositories;

import edu.miu.cs.domains.ComponentProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ComponentProduct, Integer> {
}
