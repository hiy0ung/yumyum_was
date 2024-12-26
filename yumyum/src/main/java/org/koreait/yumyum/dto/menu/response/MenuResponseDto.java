package org.koreait.yumyum.dto.menu.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.koreait.yumyum.entity.Menu;
import org.koreait.yumyum.entity.MenuOption;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponseDto {
    private Long menuId;
    private String menuName;
    private int menuPrice;
    private String imageUrl;
    private String menuDescription;
    private Boolean isAvailable;
    private String menuCategory;

    public MenuResponseDto(Menu menu) {
        this.menuId = menu.getId();
        this.menuName = menu.getMenuName();
        this.menuPrice = menu.getMenuPrice();
        this.imageUrl = menu.getImageUrl();
        this.menuDescription = menu.getMenuDescription();
        this.isAvailable = menu.getIsAvailable();
        this.menuCategory = menu.getMenuCategory().getMenuCategory();
    }

}
