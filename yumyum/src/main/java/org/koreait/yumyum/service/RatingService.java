package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.rating.response.RatingMonthResponseDto;
import org.koreait.yumyum.dto.rating.response.RatingStatisticsResponseDto;

import java.util.List;

public interface RatingService {
    ResponseDto<List<RatingStatisticsResponseDto>> getReviewCountByRating(Long id);
    ResponseDto<List<RatingMonthResponseDto>> getAvgRatingByMonth(Long id, String date);
}
