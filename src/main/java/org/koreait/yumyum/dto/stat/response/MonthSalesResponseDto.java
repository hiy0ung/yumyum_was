package org.koreait.yumyum.dto.stat.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthSalesResponseDto {
    private int orderDateYear;
    private int orderDateMonth;
    private int monthSales;
}
