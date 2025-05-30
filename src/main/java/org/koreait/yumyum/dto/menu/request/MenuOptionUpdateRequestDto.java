package org.koreait.yumyum.dto.menu.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuOptionUpdateRequestDto {
    private Long menuId;
    private String optionName;
    private List<MenuOptionDetailUpdateRequestDto> optionDetails;
}
