package org.koreait.yumyum.dto.menu.response;

import lombok.Data;
import org.koreait.yumyum.entity.MenuOptionDetail;

@Data
public class MenuOptionDetailResponseDto {
    private Long menuOptionDetailId;
    private String optionDetailName;
    private int additionalFee;

    public MenuOptionDetailResponseDto(MenuOptionDetail menuOptionDetail) {
        this.menuOptionDetailId = menuOptionDetail.getId();
        this.optionDetailName = menuOptionDetail.getOptionDetailName();
        this.additionalFee = menuOptionDetail.getAdditionalFee();
    }
}
