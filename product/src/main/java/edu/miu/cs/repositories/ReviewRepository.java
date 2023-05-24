package edu.miu.cs.repositories;

import edu.miu.cs.domains.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("select r from Review r where r.product.id = :productId")
    Optional<Page<Review>> findReviewsByProduct(int productId, Pageable pageable);

    @Query("select count(*) from Review r where r.product.id = :productId and r.reviewer.id = :reviewerId and r.orderId = :orderId")
    Optional<Integer> countDistinctByProductAndOrderIdAndReviewer(int productId, int orderId, int reviewerId);
}
