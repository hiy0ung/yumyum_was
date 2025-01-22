package org.koreait.yumyum.dto.menu.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.yumyum.entity.MenuCategory;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuCategoryResponseDto {
    private Long id;
    private String menuCategory;
    private int menuCategorySequence;

    public MenuCategoryResponseDto(MenuCategory category) {
        this.id = category.getId();
        this.menuCategory = category.getMenuCategory();
        this.menuCategorySequence = category.getMenuCategorySequence();
    }
}
