package edu.miu.cs.controllers;

import edu.miu.cs.dto.ProductDTO;
import edu.miu.cs.dto.ReviewDTO;
import edu.miu.cs.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping
    public ResponseEntity<?> addReview(@RequestParam("productId") Integer productId, @ModelAttribute("reviewDTO") ReviewDTO reviewDTO){
        var res = reviewService.addReview(productId, reviewDTO);
        return new ResponseEntity<ReviewDTO>(res, HttpStatus.OK);
    }
    @GetMapping
    public Page<ReviewDTO> getAllReviews(@RequestParam("productId") Integer productId,Pageable pageable) {
        return reviewService.getAllReviews(productId, pageable);
    }
}
