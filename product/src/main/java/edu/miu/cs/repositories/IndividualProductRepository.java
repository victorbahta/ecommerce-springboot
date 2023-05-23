package edu.miu.cs.repositories;

import edu.miu.cs.domains.IndividualProduct;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualProductRepository extends JpaRepository<IndividualProduct, Integer> {
}
