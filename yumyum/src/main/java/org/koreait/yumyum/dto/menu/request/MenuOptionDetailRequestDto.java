package org.koreait.yumyum.dto.menu.request;

import lombok.Data;

import java.util.List;

@Data
public class MenuOptionDetailRequestDto {
    private Long menuOptionId;
    private String optionDetailName;
    private int additionalFee;

}