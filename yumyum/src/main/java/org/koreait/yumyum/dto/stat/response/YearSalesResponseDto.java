package org.koreait.yumyum.dto.stat.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class YearSalesResponseDto {
    private int orderDateYear;
    private int yearSales;
}

