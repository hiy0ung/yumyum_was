package org.koreait.yumyum.dto.menu.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuGetResponseDto {
    private Long menuId;
    private String menuName;
    private Integer menuPrice;
    private String imageUrl;
    private String menuDescription;
    private Boolean isAvailable;
    private String menuCategory;
    private List<MenuOptionGetResponseDto> menuOptions;
}
