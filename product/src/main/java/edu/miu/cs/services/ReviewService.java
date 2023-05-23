package edu.miu.cs.services;

import edu.miu.cs.dto.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    ReviewDTO addReview(int productId, ReviewDTO reviewDTO);
    Page<ReviewDTO> getAllReviews(int productId, Pageable pageable);
}
