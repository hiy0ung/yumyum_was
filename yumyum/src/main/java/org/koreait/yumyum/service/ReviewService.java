package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.review.response.ReviewResponseDto;
import org.koreait.yumyum.dto.reviewComment.request.ReviewCommentRequestDto;
import org.koreait.yumyum.dto.reviewComment.response.ReviewCommentResponseDto;
import org.koreait.yumyum.dto.reviewNotice.request.ReviewNoticeRequestDto;

import java.util.List;

public interface ReviewService {
    ResponseDto<List<ReviewResponseDto>> getAllReviews(Long id);

    ResponseDto<List<ReviewResponseDto>> getUnansweredReviews(Long id);

    ResponseDto<ReviewCommentResponseDto> createReviewComment(Long id, Long reviewId, ReviewCommentRequestDto dto);

    ResponseDto<String> deleteReviewComment(Long id, Long reviewId);

}
