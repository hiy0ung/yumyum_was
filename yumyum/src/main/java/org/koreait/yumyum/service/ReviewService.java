package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.review.response.ReviewResponseDto;

import java.util.List;

public interface ReviewService {
    ResponseDto<List<ReviewResponseDto>> getAllReviews(Long id);
}
