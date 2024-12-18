package org.koreait.yumyum.dto.menu.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.yumyum.entity.MenuOption;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuOptionResponseDto {
    private Long menuOptionId;
    private String optionName;

    public MenuOptionResponseDto(MenuOption menuOption) {
        this.menuOptionId = menuOption.getId();
        this.optionName = menuOption.getOptionName();
    }

}
