package com.example.ReviewMs.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReview(Long companyId);

    Boolean createReview(Long companyId,Review review);

    Review findReviewById(Long reviewId);

    Boolean removeReviewById(Long reviewId);

    Boolean updateReviewById(Long reviewId, Review review);
}
