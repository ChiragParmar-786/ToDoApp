package com.example.ReviewMs.Review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

   @GetMapping
   public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
       List<Review> reviews = reviewService.getAllReview(companyId);

       if(reviews != null)
           return new ResponseEntity<>(reviews, HttpStatus.OK);
       else
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   @PostMapping
   public ResponseEntity<String> addReview(@RequestParam Long companyId,@RequestBody Review review){
       Boolean sts = reviewService.createReview(companyId,review);
       if(sts)
          return new ResponseEntity<>("Review added successfully",HttpStatus.OK);
       else
          return new ResponseEntity<>("Company not found for given id",HttpStatus.NOT_FOUND);
   }

   @GetMapping("/{reviewId}")
   public ResponseEntity<Review> findReviewById(@PathVariable Long reviewId){
        Review review = reviewService.findReviewById(reviewId);
        if(review != null)
            return new ResponseEntity<>(review,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   @DeleteMapping("/{reviewId}")
   public ResponseEntity<String> deleteReviewById(@PathVariable Long reviewId){
       Boolean sts = reviewService.removeReviewById(reviewId);

       if(sts)
           return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
       else
           return new ResponseEntity<>("Review or Company not found",HttpStatus.NOT_FOUND);
   }

   @PutMapping("/{reviewId}")
   public ResponseEntity<String> updateReviewById(@PathVariable Long reviewId, @RequestBody Review review){
        Boolean sts = reviewService.updateReviewById(reviewId,review);

       if(sts)
           return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
       else
           return new ResponseEntity<>("Review or Company not found",HttpStatus.NOT_FOUND);
   }





}
