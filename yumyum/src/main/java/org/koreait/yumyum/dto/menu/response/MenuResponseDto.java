package org.koreait.yumyum.dto.menu.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.koreait.yumyum.entity.Menu;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponseDto {
    private String menuName;
    private String imageUrl;
    private String menuDescription;
    private int menuPrice;
    private Boolean isAvailable;
    private String menuCategory;
    private Long categoryId;
    private List<MenuOptionResponseDto> menuOptions;

    public MenuResponseDto(Menu menu) {
        this.menuName = menu.getMenuName();
        this.imageUrl = menu.getImageUrl();
        this.menuDescription = menu.getMenuDescription();
        this.menuPrice = menu.getMenuPrice();
        this.isAvailable = menu.getIsAvailable();
        this.menuCategory = menu.getMenuCategory().getMenuCategory();
        this.categoryId = menu.getMenuCategory().getId();
        this.menuOptions = menu.getMenuOptions().stream()
                .map(MenuOptionResponseDto::new)
                .collect(Collectors.toList());
    }

}
