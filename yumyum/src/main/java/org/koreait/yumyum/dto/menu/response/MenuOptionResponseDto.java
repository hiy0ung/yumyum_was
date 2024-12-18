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
    private Long id;
    private String optionName;
    private Long menuId;
    private List<MenuOptionDetailResponseDto> menuOptionDetails;

    public MenuOptionResponseDto(MenuOption menuOption) {
        this.id = menuOption.getId();
        this.optionName = menuOption.getOptionName();
        this.menuId = menuOption.getMenu().getId();
        this.menuOptionDetails = menuOption.getMenuOptionDetails().stream()
                .map(MenuOptionDetailResponseDto::new)
                .collect(Collectors.toList());
    }

}
