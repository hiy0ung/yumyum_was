package org.koreait.yumyum.dto.menu.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuOptionDetailGetResponseDto {
    private Long detailId;
    private String optionDetailName;
    private Integer additionalFee;
}
