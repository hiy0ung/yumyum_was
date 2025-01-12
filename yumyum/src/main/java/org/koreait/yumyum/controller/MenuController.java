package org.koreait.yumyum.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ApiMappingPattern;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionRequestDto;
import org.koreait.yumyum.dto.menu.request.MenuUpdateRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuGetResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuResponseDto;
import org.koreait.yumyum.entity.MenuOption;
import org.koreait.yumyum.service.MenuService;
import org.koreait.yumyum.service.implement.MenuImageServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.MENU)
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    private final MenuImageServiceImpl menuImageService;

    public static final String MENU_POST_ADD = "/add";
    public static final String MENU_GET_LIST = "/";
    public static final String MENU_GET_ID = "/{menuId}";
    public static final String MENU_PUT_UPDATE = "/update/{menuId}";
    public static final String MENU_DELETE = "/delete/{menuId}";


    // 메뉴 추가
    @PostMapping(MENU_POST_ADD)
    public ResponseEntity<ResponseDto<MenuResponseDto>> addMenu(@Valid @ModelAttribute MenuRequestDto dto, @AuthenticationPrincipal Long id) {
        ResponseDto<MenuResponseDto> response = menuService.addMenu(dto, id);
        HttpStatus status = response.isResult() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 메뉴 조회
    @GetMapping(MENU_GET_LIST)
    public ResponseEntity<ResponseDto<List<MenuGetResponseDto>>> getAllMenus(@AuthenticationPrincipal Long id) {
        ResponseDto<List<MenuGetResponseDto>> result = menuService.getAllMenus(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 특정 ID 메뉴 조회
    @GetMapping(MENU_GET_ID)
    public ResponseEntity<ResponseDto<MenuGetResponseDto>> getMenusById(@PathVariable Long menuId, @AuthenticationPrincipal Long id) {
        ResponseDto<MenuGetResponseDto> response = menuService.getMenusById(menuId, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 메뉴 수정
    @PostMapping(MENU_PUT_UPDATE)
    public ResponseEntity<ResponseDto<MenuResponseDto>> updateMenu(
            @Valid @PathVariable Long menuId,
            @ModelAttribute MenuUpdateRequestDto dto,
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<MenuResponseDto> response = menuService.updateMenu(menuId, dto, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 메뉴 삭제
    @DeleteMapping(MENU_DELETE)
    public ResponseEntity<ResponseDto<Void>> deleteMenu(
            @PathVariable Long menuId,
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<Void> response = menuService.deleteMenu(menuId, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
