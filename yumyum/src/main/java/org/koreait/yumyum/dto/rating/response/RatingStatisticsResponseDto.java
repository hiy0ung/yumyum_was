package org.koreait.yumyum.dto.rating.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingStatisticsResponseDto {
    private int rating;
    private Long reviewCount;

    public RatingStatisticsResponseDto(int rating, long reviewCount) {
        this.rating = rating;
        this.reviewCount = reviewCount;
    }
}
