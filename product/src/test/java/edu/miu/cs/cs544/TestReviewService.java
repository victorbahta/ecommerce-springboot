package edu.miu.cs.cs544;

import edu.miu.cs.domains.CompositeProduct;
import edu.miu.cs.domains.Review;
import edu.miu.cs.domains.Reviewer;
import edu.miu.cs.dto.CustomerDTO;
import edu.miu.cs.dto.ReviewDTO;
import edu.miu.cs.feign.CustomerService;
import edu.miu.cs.feign.OrderService;
import edu.miu.cs.repositories.ComponentProductRepository;
import edu.miu.cs.repositories.ReviewRepository;
import edu.miu.cs.services.ReviewServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TestReviewService {

    @InjectMocks
    private ReviewServiceImp reviewService;
    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private CustomerService customerService;
    @Mock
    private OrderService orderService;
    @Mock
    private ComponentProductRepository componentProductRepository;

    @Before
    public void setUpAddReview() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void whenAddReviewThenShouldGetNewReview() {
        Review review = new Review();
        review.setId(1);
        review.setTitle("Review 1");
        review.setOrderId(10);
        CompositeProduct product = new CompositeProduct();
        product.setId(50);
        product.setName("Product name 50");
        review.setProduct(product);
        Reviewer reviewer = new Reviewer();
        reviewer.setId(30);
        reviewer.setFirstname("Test Firstname");
        reviewer.setLastname("Test Lastname");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(reviewer.getId());
        customerDTO.setFirstName(reviewer.getFirstname());
        customerDTO.setLastName(reviewer.getLastname());

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setOrderId(review.getOrderId());
        reviewDTO.setReviewerId(reviewer.getId());

        // Mock the behavior of the repository to save the review
        when(reviewRepository.save(review)).thenReturn(review);
        when(componentProductRepository.getReferenceById(product.getId())).thenReturn(product);
        when(orderService.checkProductOrderedByCustomer(reviewer.getId(), review.getOrderId(), product.getId())).thenReturn(Optional.of(true));
        when(reviewRepository.countDistinctByProductAndOrderIdAndReviewer(product.getId(), review.getOrderId(), reviewer.getId())).thenReturn(Optional.of(0));
        when(customerService.getCustomerById(reviewer.getId())).thenReturn(customerDTO);

        // Call the service to add the review
        reviewService.addReview(product.getId(), reviewDTO);

        // Verify that the repository's save method was called with the review
        verify(reviewRepository).save(review);

    }
}
