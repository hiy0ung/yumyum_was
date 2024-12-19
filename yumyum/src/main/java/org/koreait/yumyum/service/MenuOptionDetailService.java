package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionDetailRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuOptionDetailResponseDto;

public interface MenuOptionDetailService {
    ResponseDto<MenuOptionDetailResponseDto> addOptionDetail(MenuOptionDetailRequestDto dto);

    ResponseDto<MenuOptionDetailResponseDto> updateOptionDetail(MenuOptionDetailRequestDto dto, Long id);

    ResponseDto<Void> deleteOptionDetail(Long id);
}
