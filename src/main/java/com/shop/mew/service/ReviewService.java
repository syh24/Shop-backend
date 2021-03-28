package com.shop.mew.service;

import com.shop.mew.domain.review.Review;
import com.shop.mew.domain.review.ReviewRepository;
import com.shop.mew.web.dto.ReviewRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review writeReview(ReviewRequestDto request) {
        return reviewRepository.save(new Review(request.getContent(), request.getRate()));
    }

    public List<Review> findAllReview() {
       return reviewRepository.findAll();
    }

}
