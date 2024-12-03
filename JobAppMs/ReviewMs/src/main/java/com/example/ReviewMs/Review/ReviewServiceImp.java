package com.example.ReviewMs.Review;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {

    ReviewRepository reviewRepository;


    public ReviewServiceImp(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReview(Long companyId) {

        if( companyId != null) {
            return reviewRepository.findByCompanyId(companyId);
        }
        return null;
    }

    @Override
    public Boolean createReview(Long companyId,Review review) {

        if( companyId != null)
        {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Review findReviewById(Long reviewId) {
       return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public Boolean removeReviewById(Long reviewId) {

        if(reviewRepository.existsById(reviewId)){
            reviewRepository.deleteById(reviewId);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Boolean updateReviewById(Long reviewId, Review review) {

        Review reviewToUpdate = reviewRepository.findById(reviewId).orElse(null);

         if(reviewToUpdate != null)
         {
             reviewToUpdate.setTitle(review.getTitle());
             reviewToUpdate.setDescription(review.getDescription());
             reviewToUpdate.setRating(review.getRating());
             reviewRepository.save(reviewToUpdate);
             return true;
         }
         else {
             return false;
         }
    }
}
