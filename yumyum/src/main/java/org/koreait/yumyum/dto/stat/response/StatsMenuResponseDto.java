package org.koreait.yumyum.dto.stat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatsMenuResponseDto {
    private String orderProductName;
    private Long totalQuantitySold;

    public StatsMenuResponseDto(StatsMenuResponseDto value) {
        this.orderProductName = value.getOrderProductName();
        this.totalQuantitySold = value.getTotalQuantitySold();
    }
}
