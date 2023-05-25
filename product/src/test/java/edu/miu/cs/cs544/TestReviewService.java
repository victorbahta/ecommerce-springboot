package edu.miu.cs.cs544;

import edu.miu.cs.domains.*;
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
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

import static org.mockito.Mockito.*;

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
    @Mock
    private ModelMapper modelMapper;

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

        IndividualProduct product1 = new IndividualProduct();
        product1.setId(50);
        product1.setName("Product name 50");
        review.setProduct(product1);

        Reviewer reviewer = new Reviewer();
        reviewer.setId(30);
        reviewer.setFirstname("Test Firstname");
        reviewer.setLastname("Test Lastname");
        review.setReviewer(reviewer);

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
        when(componentProductRepository.getReferenceById(product1.getId())).thenReturn(product1);
        when(orderService.checkProductOrderedByCustomer(reviewer.getId(), review.getOrderId(), product1.getId())).thenReturn(Optional.of(true));
//        when(reviewRepository.countDistinctByProductAndOrderIdAndReviewer(50, 10, 30)).thenReturn(Optional.empty());
        when(reviewRepository.countDistinctByProductAndOrderIdAndReviewer(product1.getId(), review.getOrderId(), reviewer.getId())).thenReturn(Optional.empty());
        when(customerService.getCustomerById(reviewer.getId())).thenReturn(customerDTO);
        when(modelMapper.map(reviewDTO, Review.class)).thenReturn(review);
        when(reviewRepository.save(review)).thenReturn(review);

        // Call the service to add the review
        reviewService.addReview(product1.getId(), reviewDTO);

        // Verify that the repository's save method was called with the review
        verify(reviewRepository).save(review);

    }
}
