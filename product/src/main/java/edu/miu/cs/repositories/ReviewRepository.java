package edu.miu.cs.repositories;

import edu.miu.cs.domains.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("select r from Review r where r.product.id = :productId")
    Page<Review> findReviewsByProduct(int productId, Pageable pageable);
}
