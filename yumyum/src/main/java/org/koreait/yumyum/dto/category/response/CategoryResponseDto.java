package org.koreait.yumyum.dto.category.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.yumyum.entity.MenuCategory;

@Data
@NoArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String menuCategory;

    public CategoryResponseDto(MenuCategory menuCategory) {
        this.id = menuCategory.getId();
        this.menuCategory = menuCategory.getMenuCategory();
    }
}
