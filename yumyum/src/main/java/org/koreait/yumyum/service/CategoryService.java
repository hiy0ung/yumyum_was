package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuCategoryRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuCategoryResponseDto;

import java.util.List;

public interface CategoryService {
    ResponseDto<List<MenuCategoryResponseDto>> getAllMenuCategory(Long id);
    ResponseDto<List<MenuCategoryResponseDto>> updateSequenceCategory(MenuCategoryRequestDto dto);
    ResponseDto<MenuCategoryResponseDto> createCategory(Long id, MenuCategoryRequestDto dto);
    ResponseDto<Void> deleteCategory(Long categoryId);
}
