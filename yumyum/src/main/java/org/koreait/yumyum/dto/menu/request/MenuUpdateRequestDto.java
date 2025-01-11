package org.koreait.yumyum.dto.menu.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuUpdateRequestDto {
    private Long categoryId;
    private String menuName;
    private String menuDescription;
    private int menuPrice;
    private Boolean isAvailable;
    private List<MenuOptionUpdateRequestDto> menuOptions;
}
