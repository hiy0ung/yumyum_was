package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuUpdateRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuGetResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


import java.util.List;

public interface MenuService {
    ResponseDto<MenuResponseDto> addMenu(MenuRequestDto dto, @AuthenticationPrincipal Long id);
    ResponseDto<List<MenuGetResponseDto>> getAllMenus(Long id);
    ResponseDto<MenuGetResponseDto> getMenusById(Long menuId, Long id);
    ResponseDto<MenuResponseDto> updateMenu(Long menuId, MenuUpdateRequestDto dto, @AuthenticationPrincipal Long id);
    ResponseDto<Void> deleteMenu(Long menuId, Long id);
    ResponseDto<MenuResponseDto> updateMenuState(Long menuId, boolean isAvailable);
}
