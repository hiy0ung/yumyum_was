package org.koreait.yumyum.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ApiMappingPattern;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.order.response.OrderResponseDto;
import org.koreait.yumyum.dto.review.response.ReviewResponseDto;
import org.koreait.yumyum.dto.reviewComment.request.ReviewCommentRequestDto;
import org.koreait.yumyum.dto.reviewComment.response.ReviewCommentResponseDto;
import org.koreait.yumyum.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.REVIEW) // "/api/v1/reviews"
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private static final String GET_REVIEWS = "/";
    private static final String GET_UNANSWERED_REVIEWS = "/unanswered";
    private static final String CREATE_REVIEWS_COMMENT = "/comment/create/{reviewId}";
    private static final String DELETE_REVIEWS_COMMENT = "/comment/delete/{reviewId}";


    @GetMapping(GET_REVIEWS)
    public ResponseEntity<ResponseDto<List<ReviewResponseDto>>> getAllReviews(
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<List<ReviewResponseDto>> response = reviewService.getAllReviews(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(GET_UNANSWERED_REVIEWS)
    public ResponseEntity<ResponseDto<List<ReviewResponseDto>>> getUnansweredReviews(@AuthenticationPrincipal Long id) {
        ResponseDto<List<ReviewResponseDto>> response = reviewService.getUnansweredReviews(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(CREATE_REVIEWS_COMMENT)
    public ResponseEntity<ResponseDto<ReviewCommentResponseDto>> createReviewComment(@AuthenticationPrincipal Long id, @PathVariable Long reviewId, @RequestBody ReviewCommentRequestDto dto) {
        ResponseDto<ReviewCommentResponseDto> response = reviewService.createReviewComment(id, reviewId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(DELETE_REVIEWS_COMMENT)
    public ResponseEntity<ResponseDto<String>> deleteReviewComment(@AuthenticationPrincipal Long id, @PathVariable Long reviewId) {
        ResponseDto<String> response = reviewService.deleteReviewComment(id, reviewId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
