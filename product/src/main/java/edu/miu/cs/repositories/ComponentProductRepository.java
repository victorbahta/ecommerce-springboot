package edu.miu.cs.repositories;

import edu.miu.cs.domains.ComponentProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ComponentProductRepository extends JpaRepository<ComponentProduct, Integer> {
    Page<ComponentProduct> findByIsActiveTrue(Pageable pageable);

    @Query(value = "select p.product_type from Product p where p.id = :id and p.is_active = 1", nativeQuery = true)
    Optional<String> findProductTypeById(Integer id);

    Page<ComponentProduct> findByIsActiveTrueAndNameContainingOrIsActiveTrueAndDescriptionContaining(String name, String description, Pageable pageable);
//    Page<ComponentProduct> findByIsActiveTrueAndNameContainingOrDescriptionContaining(String name, String description, Pageable pageable);
}
