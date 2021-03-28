package com.shop.mew.web;

import com.shop.mew.domain.review.Review;
import com.shop.mew.exception.NotExistOrderException;
import com.shop.mew.service.CartService;
import com.shop.mew.service.ReviewService;
import com.shop.mew.web.dto.ReviewRequestDto;
import com.shop.mew.web.dto.ReviewResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "상품 리뷰 APIs")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;
    private final CartService cartService;

    @ApiOperation(value = "리뷰 등록")
    @PostMapping("/reviews")
    public ResponseEntity<ReviewResponseDto> addReview(@RequestBody ReviewRequestDto request) {
        if (cartService.findCartByUserAndItem(request.getUserId(), request.getItemId())) {
            Review review = reviewService.writeReview(request);
            return ResponseEntity.ok(ReviewResponseDto.builder().content(review.getContent()).rate(review.getRate()).build());
        } else {
            throw new NotExistOrderException("주문하지 않은 상품입니다.");
        }
    }

    @ApiOperation(value = "모든 리뷰 조회")
    @GetMapping("/reviews")
    public List<ReviewResponseDto> getAllReview() {
        return reviewService.findAllReview()
                .stream()
                .map(review -> ReviewResponseDto.builder()
                        .content(review.getContent())
                        .rate(review.getRate())
                        .build())
                .collect(Collectors.toList());
    }
}
