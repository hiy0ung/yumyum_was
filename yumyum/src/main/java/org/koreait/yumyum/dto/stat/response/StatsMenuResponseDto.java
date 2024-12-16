package org.koreait.yumyum.dto.stat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

    public StatsMenuResponseDto(LocalDate localDate, String s, long l, long l1) {
    }
}
