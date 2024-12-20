package org.koreait.yumyum.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ApiMappingPattern;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuOptionResponseDto;
import org.koreait.yumyum.service.MenuOptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.MENU_OPTION)
@RequiredArgsConstructor
public class MenuOptionController {

    private final MenuOptionService menuOptionService;

    public static final String OPTION_POST_ADD = "/add";
    public static final String OPTION_PUT_ID = "/{id}";
    public static final String OPTION_DELETE_ID = "/{id}";

    // 추가
    @PostMapping(OPTION_POST_ADD)
    public ResponseEntity<ResponseDto<MenuOptionResponseDto>> addMenuOption(@RequestBody MenuOptionRequestDto dto) {
        ResponseDto<MenuOptionResponseDto> response = menuOptionService.addMenuOption(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 수정
    @PutMapping(OPTION_PUT_ID)
    public ResponseEntity<ResponseDto<MenuOptionResponseDto>> updateMenuOption(@RequestBody MenuOptionRequestDto dto, @PathVariable Long id) {
        ResponseDto<MenuOptionResponseDto> response = menuOptionService.updateMenuOption(dto, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 삭제
    @DeleteMapping(OPTION_DELETE_ID)
    public ResponseEntity<ResponseDto<Void>> deleteMenuOption(@PathVariable Long id) {
        ResponseDto<Void> response = menuOptionService.deleteMenuOption(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}

