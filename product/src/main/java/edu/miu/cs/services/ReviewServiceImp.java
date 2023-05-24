package edu.miu.cs.services;

import edu.miu.cs.domains.ComponentProduct;
import edu.miu.cs.domains.Review;
import edu.miu.cs.domains.Reviewer;
import edu.miu.cs.dto.CustomerDTO;
import edu.miu.cs.dto.ReviewDTO;
import edu.miu.cs.feign.CustomerService;
import edu.miu.cs.feign.OrderService;
import edu.miu.cs.repositories.ComponentProductRepository;
import edu.miu.cs.repositories.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReviewServiceImp implements ReviewService{
    @Autowired
    private ComponentProductRepository componentProductRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Override
    public ReviewDTO addReview(int productId, ReviewDTO reviewDTO) {
        try {
            ComponentProduct product = componentProductRepository.getReferenceById(productId);
            if (product == null) {
                log.error("Could not find the product " + productId+ " by id");
                return new ReviewDTO();
            }

            Optional<Integer> reviewCount = reviewRepository.countDistinctByProductAndOrderIdAndReviewer(productId
                    , reviewDTO.getOrderId(), reviewDTO.getReviewerId());
            if (!reviewCount.isPresent() || reviewCount.get() == 1) {
                log.error("Customer " + reviewDTO.getReviewerId() + " could not review a product " + productId + " of order " + reviewDTO.getOrderId() + " twice");
                return new ReviewDTO();
            }

            Optional<Boolean> isBoughtProduct = orderService.checkProductOrderedByCustomer(reviewDTO.getReviewerId()
                    , reviewDTO.getOrderId(), productId);
            System.out.println(isBoughtProduct);

            if (!isBoughtProduct.isPresent() || !isBoughtProduct.get()) {
                log.error("Customer " + reviewDTO.getReviewerId() + " could only review purchased products");
                return new ReviewDTO();
            }

            CustomerDTO customer = customerService.getCustomerById(reviewDTO.getReviewerId());

            Review review = modelMapper.map(reviewDTO, Review.class);
            review.setReviewDate(new Date());
            review.setProduct(product);

            Reviewer reviewer = new Reviewer();
            reviewer.setId(customer.getId());
            reviewer.setFirstname(customer.getFirstName());
            reviewer.setLastname(customer.getLastName());
            review.setReviewer(reviewer);
            return modelMapper.map(reviewRepository.save(review), ReviewDTO.class);
        } catch (Exception e) {
            return new ReviewDTO();
        }
    }

    @Override
    public Page<ReviewDTO> getAllReviews(int productId, Pageable pageable) {
        try {
            var result = reviewRepository.findReviewsByProduct(productId, pageable);
            if (result.isPresent()) {
                return result.get().map(review -> modelMapper.map(review, ReviewDTO.class));
            } else {
                log.error("Could not find the review of product " + productId);
                List<ReviewDTO> emptyList = new ArrayList<>();
                return new PageImpl<ReviewDTO>(emptyList, pageable, 0);
            }
        } catch (Exception ex){
            List<ReviewDTO> emptyList = new ArrayList<>();
            return new PageImpl<ReviewDTO>(emptyList, pageable, 0);
        }
    }
}
