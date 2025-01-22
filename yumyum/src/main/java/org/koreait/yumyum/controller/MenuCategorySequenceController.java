package org.koreait.yumyum.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ApiMappingPattern;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuCategoryRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuCategoryResponseDto;
import org.koreait.yumyum.service.MenuCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.CATEGORY)
@RequiredArgsConstructor
public class MenuCategorySequenceController {
    private final MenuCategoryService menuCategoryService;
    private static final String MENU_CATEGORY_POST = "/post";
    private static final String MENU_CATEGORY_GET = "/get";
    private static final String MENU_CATEGORY_SEQUENCE = "/sequence";
    private static final String MENU_CATEGORY_DELETE = "/delete/{categoryId}";

    @PostMapping(MENU_CATEGORY_POST)
    public ResponseEntity<ResponseDto> createMenuCategory(@AuthenticationPrincipal Long id, @RequestBody MenuCategoryRequestDto dto) {
        ResponseDto<MenuCategoryResponseDto> response = menuCategoryService.createCategory(id, dto);
        HttpStatus status = response.isResult() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(MENU_CATEGORY_GET)
    public ResponseEntity<ResponseDto<List<MenuCategoryResponseDto>>> getMenuCategory(@AuthenticationPrincipal Long id) {
        ResponseDto<List<MenuCategoryResponseDto>> response = menuCategoryService.getAllMenuCategory(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(MENU_CATEGORY_SEQUENCE)
    public ResponseEntity<ResponseDto<List<MenuCategoryResponseDto>>> updateMenuCategorySequence(@RequestBody MenuCategoryRequestDto dto) {
        ResponseDto<List<MenuCategoryResponseDto>> response = menuCategoryService.updateSequenceCategory(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(MENU_CATEGORY_DELETE)
    public ResponseEntity<ResponseDto<Void>> deleteMenuCategory(@PathVariable Long categoryId) {
        ResponseDto<Void> responseDto = menuCategoryService.deleteCategory(categoryId);
        HttpStatus status = responseDto.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(responseDto);
    }

}
