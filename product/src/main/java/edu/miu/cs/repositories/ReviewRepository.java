package edu.miu.cs.repositories;

import edu.miu.cs.domains.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
