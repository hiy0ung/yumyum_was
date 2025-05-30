package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionRequestDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionUpdateRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuOptionResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface MenuOptionService {
    ResponseDto<MenuOptionResponseDto> addMenuOption(MenuOptionRequestDto dto, Long id);
    ResponseDto<MenuOptionResponseDto> updateMenuOption(MenuOptionUpdateRequestDto dto, Long optionId, Long id);
    ResponseDto<Void> deleteMenuOption(Long optionId, Long id);
}
