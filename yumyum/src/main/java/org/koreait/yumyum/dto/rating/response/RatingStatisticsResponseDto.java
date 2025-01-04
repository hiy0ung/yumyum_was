package org.koreait.yumyum.dto.rating.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingStatisticsResponseDto {
    private double rating;
    private Long reviewCount;

    public RatingStatisticsResponseDto(double rating, long reviewCount) {
        this.rating = rating;
        this.reviewCount = reviewCount;
    }
}
