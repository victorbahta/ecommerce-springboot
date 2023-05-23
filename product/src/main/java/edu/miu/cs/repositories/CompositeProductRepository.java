package edu.miu.cs.repositories;

import edu.miu.cs.domains.ComponentProduct;
import edu.miu.cs.domains.CompositeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompositeProductRepository extends JpaRepository<CompositeProduct, Integer> {
    @Query("select cp from CompositeProduct cp join fetch cp.productItems pi where cp.id = :id and cp.isActive = true")
    Optional<CompositeProduct> findProductAndItemsById(Integer id);
}
