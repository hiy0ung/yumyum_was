package org.koreait.yumyum.dto.rating.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingMonthResponseDto {
    private int reviewMonth;
    private int avgRating;
}
