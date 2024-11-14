package org.koreait.yumyum.dto.menu;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuAllResponseDto {
    private String menuName;
    private String imageUrl;
    private String menuDescription;
    private int menuPrice;
    private boolean isAvailable;
    private String menuCategory;

    @Builder
    public MenuAllResponseDto(MenuAllResponseDto menu) {
        this.menuName = menu.getMenuName();
        this.imageUrl = menu.getImageUrl();
        this.menuDescription = menu.getMenuDescription();
        this.menuPrice = menu.getMenuPrice();
        this.isAvailable = menu.isAvailable();
        this.menuCategory = menu.getMenuCategory();
    }

}