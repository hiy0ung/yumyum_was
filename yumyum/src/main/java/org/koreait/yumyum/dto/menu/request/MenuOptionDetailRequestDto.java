package org.koreait.yumyum.dto.menu.request;

import lombok.Data;

@Data
public class MenuOptionDetailRequestDto {
    private Long menuOptionId;
    //    private Long orderDetailId;
    private String optionDetailName;
    private String additionalFee;
}