package edu.miu.cs.controllers;

import edu.miu.cs.domains.CompositeProduct;
import edu.miu.cs.dto.ProductDTO;
import edu.miu.cs.dto.ProductType;
import edu.miu.cs.dto.ReviewDTO;
import edu.miu.cs.services.ProductService;
import edu.miu.cs.services.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    @Transactional
    public Page<ProductDTO> getAllProducts(Pageable pageable, @RequestParam(value = "search", required = false) String searchString) {
        System.out.println(searchString);
        if (searchString == null || searchString.length() == 0){
            return productService.getAll(pageable);
        }
        return productService.getByNameOrDescription(searchString, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id){
        var res = productService.getById(id);
        return new ResponseEntity<ProductDTO>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO){
        var res = productService.add(productDTO);
        if (res != null) {
            return new ResponseEntity<ProductDTO>(res, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Could not add new product", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{id}/productItem")
    @Transactional
    public ResponseEntity<?> addProductItem(@PathVariable Integer id, @RequestBody ProductDTO productDTO){
        ProductDTO productFromId = productService.getById(id);
        if (productFromId != null && productFromId.getProductType() == ProductType.composite) {
            ProductDTO res = productService.addProductItem(id, productDTO);
            return new ResponseEntity<ProductDTO>(res, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Could not add product item", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        boolean res = productService.delete(id);
        if (res == true) {
            return new ResponseEntity<Boolean>(res, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Could not delete product", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO){
        boolean res = productService.update(id, productDTO);
        if (res == true) {
            return new ResponseEntity<Boolean>(res, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Could not update product", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/{id}/reviews")
    public ResponseEntity<?> addReviewToProduct(@RequestHeader("userId") Integer reviewerId, @PathVariable Integer id, @RequestBody ReviewDTO reviewDTO){
        reviewDTO.setReviewerId(reviewerId);
        ReviewDTO res = reviewService.addReview(id, reviewDTO);
        if (res != null) {
            return new ResponseEntity<ReviewDTO>(res, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Add review is failed", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}/reviews")
    public Page<ReviewDTO> getReviewsOfProduct(@PathVariable Integer id, Pageable pageable){
        return reviewService.getAllReviews(id, pageable);
    }
}
