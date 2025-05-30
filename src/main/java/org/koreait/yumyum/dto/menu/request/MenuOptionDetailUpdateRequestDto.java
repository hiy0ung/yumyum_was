package org.koreait.yumyum.dto.menu.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuOptionDetailUpdateRequestDto {
    private Long menuOptionId;
    private List<MenuOptionDetailNameRequestDto> detailName;
}
