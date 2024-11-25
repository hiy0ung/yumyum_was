package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuOptionResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuOptionService {
    ResponseDto<MenuOptionResponseDto> addMenuOption(MenuOptionRequestDto dto);

    ResponseDto<List<MenuOptionResponseDto>> getAllMenuOptions();

    ResponseDto<MenuOptionResponseDto> getMenuOptionById(Long id);

    ResponseDto<MenuOptionResponseDto> updateMenuOption(MenuOptionRequestDto dto, Long id);

    ResponseDto<Void> deleteMenuOption(Long id);
}
