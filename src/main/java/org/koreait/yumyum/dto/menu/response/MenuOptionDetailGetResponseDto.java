package org.koreait.yumyum.dto.menu.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuOptionDetailGetResponseDto {
    private Long detailId;
    private String optionDetailName;
    private Integer additionalFee;
}
