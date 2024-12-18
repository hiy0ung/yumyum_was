package org.koreait.yumyum.service;

import jakarta.validation.Valid;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.response.MenuGetResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    ResponseDto<MenuResponseDto> addMenu(@Valid MenuRequestDto dto);
    ResponseDto<List<MenuGetResponseDto>> getAllMenus();
    ResponseDto<MenuGetResponseDto> getMenusById(Long id);
    ResponseDto<MenuResponseDto> updateMenu(@Valid Long id, MenuRequestDto dto);
    ResponseDto<Void> deleteMenu(Long id);
}
