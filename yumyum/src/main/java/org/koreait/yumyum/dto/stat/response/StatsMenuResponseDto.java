package org.koreait.yumyum.dto.stat.response;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsMenuResponseDto {
    private LocalDate orderDate;
    private String menuName;
    private Integer quantity;
    private Long sumTotalPrice;
}
