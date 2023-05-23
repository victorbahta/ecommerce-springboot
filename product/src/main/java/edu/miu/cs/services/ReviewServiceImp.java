package edu.miu.cs.services;

import com.netflix.discovery.converters.Auto;
import edu.miu.cs.domains.ComponentProduct;
import edu.miu.cs.domains.Review;
import edu.miu.cs.dto.ProductDTO;
import edu.miu.cs.dto.ReviewDTO;
import edu.miu.cs.repositories.ComponentProductRepository;
import edu.miu.cs.repositories.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService{
    @Autowired
    private ComponentProductRepository componentProductRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ReviewDTO addReview(int productId, ReviewDTO reviewDTO) {
        try {
            var product = componentProductRepository.getReferenceById(productId);
            if (product == null) {
                return new ReviewDTO();
            }
            Review review = modelMapper.map(reviewDTO, Review.class);
            review.setReviewDate(new Date());
            review.setProduct(product);
            return modelMapper.map(reviewRepository.save(review), ReviewDTO.class);
        } catch (Exception e) {
            return new ReviewDTO();
        }
    }

    @Override
    public Page<ReviewDTO> getAllReviews(int productId, Pageable pageable) {
        try {
            var result = reviewRepository.findReviewsByProduct(productId, pageable);
            return result.map(review -> modelMapper.map(review, ReviewDTO.class));
        } catch (Exception ex){
            List<ReviewDTO> emptyList = new ArrayList<>();
            return new PageImpl<ReviewDTO>(emptyList, pageable, 0);
        }
    }
}
