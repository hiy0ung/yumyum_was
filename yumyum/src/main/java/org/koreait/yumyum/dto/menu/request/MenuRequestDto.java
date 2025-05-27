package org.koreait.yumyum.dto.menu.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuRequestDto {
    private Long categoryId;
    private String menuName;
    private MultipartFile imageUrl;
    private String menuDescription;
    private int menuPrice;
    private Boolean isAvailable;
    private List<MenuOptionRequestDto> menuOptions;
}
