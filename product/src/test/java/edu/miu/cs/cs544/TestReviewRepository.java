package edu.miu.cs.cs544;

import edu.miu.cs.domains.CompositeProduct;
import edu.miu.cs.domains.Review;
import edu.miu.cs.domains.Reviewer;
import edu.miu.cs.repositories.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestReviewRepository {
    @Mock
    private ReviewRepository reviewRepository;

    private Pageable pageable = Pageable.ofSize(10);

    @Test
    public void whenFindReviewsByProduct_thenReturnReview(){
        //Given
        Review review = new Review();
        review.setTitle("Review 1");
        review.setDescription("Review Description 1");
        review.setId(1);
        review.setOrderId(10);

        CompositeProduct product = new CompositeProduct();
        product.setId(50);
        product.setName("Product name 50");
        review.setProduct(product);

        List<Review> reviews = new ArrayList<>();
        reviews.add(review);
        Page<Review> reviewPage = new PageImpl<>(reviews);

        //When
        when(reviewRepository.findReviewsByProduct(review.getProduct().getId(), pageable)).thenReturn(Optional.of(reviewPage));;
        Optional<Page<Review>> found = reviewRepository.findReviewsByProduct(review.getProduct().getId(), pageable);

        assertTrue(found.isPresent());
        //Then
        assertEquals(found.get(), reviewPage);
    }
}
