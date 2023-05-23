package edu.miu.cs.repositories;

import edu.miu.cs.domains.ComponentProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComponentProductRepository extends JpaRepository<ComponentProduct, Integer> {
    Page<ComponentProduct> findByIsActiveTrue(Pageable pageable);

    @Query("select cp from CompositeProduct cp join fetch cp.productItems pi where cp.id = :id and  cp.isActive = true and cp.isActive = true")
    ComponentProduct findProductItemsById(Integer id);

    Page<ComponentProduct> findByNameOrDescription(String name, String description);
}
