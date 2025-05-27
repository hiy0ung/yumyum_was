package org.koreait.yumyum.dto.menu.request;

import lombok.Data;

import java.util.List;

@Data
public class MenuOptionRequestDto {
    private Long menuId;
    private String optionName;
    private List<MenuOptionDetailRequestDto> optionDetails;
}
