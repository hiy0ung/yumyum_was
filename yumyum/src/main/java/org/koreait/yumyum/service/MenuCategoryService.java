package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuCategoryRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuCategoryResponseDto;

import java.util.List;

public interface MenuCategoryService {
    ResponseDto<List<MenuCategoryResponseDto>> getAllMenuCategory();
    ResponseDto<List<MenuCategoryResponseDto>> updateSequenceCategory(MenuCategoryRequestDto dto);

    ResponseDto<MenuCategoryResponseDto> createCategory(MenuCategoryRequestDto dto);
}
