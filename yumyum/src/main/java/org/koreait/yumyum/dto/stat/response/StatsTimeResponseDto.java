package org.koreait.yumyum.dto.stat.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class StatsTimeResponseDto {
    private LocalDate date;

    private Integer hour;

    private Long revenue;
}
