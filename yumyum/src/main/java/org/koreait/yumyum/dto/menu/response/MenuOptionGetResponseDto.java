package org.koreait.yumyum.dto.menu.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuOptionGetResponseDto {
    private Long menuOptionId;
    private String optionName;

    private List<MenuOptionDetailGetResponseDto> optionDetails;
}
