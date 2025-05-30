package org.koreait.yumyum.dto.stat.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DailySalesResponseDto {
    private LocalDate orderDay;
    private int dailySales;


}
