package org.koreait.yumyum.dto.menu.response;

import lombok.Data;
import org.koreait.yumyum.entity.MenuOptionDetail;

@Data
public class MenuOptionDetailResponseDto {
    private Long id;
    private String optionDetailName;
    private String additionalFee;

    public MenuOptionDetailResponseDto(MenuOptionDetail menuOptionDetail) {
        this.id = menuOptionDetail.getId();
        this.optionDetailName = menuOptionDetail.getOptionDetailName();
        this.additionalFee = menuOptionDetail.getAdditionalFee();
    }
}
