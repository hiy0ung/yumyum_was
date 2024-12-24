package org.koreait.yumyum.service;

import jakarta.validation.Valid;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.response.MenuGetResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface MenuService {
    ResponseDto<MenuResponseDto> addMenu(@Valid MenuRequestDto dto, @AuthenticationPrincipal Long id);
    ResponseDto<List<MenuGetResponseDto>> getAllMenus();
    ResponseDto<MenuGetResponseDto> getMenusById(Long id);
    ResponseDto<MenuResponseDto> updateMenu(@Valid Long id, MenuRequestDto dto);
    ResponseDto<Void> deleteMenu(Long id);
}
