package org.koreait.yumyum.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ApiMappingPattern;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionRequestDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionUpdateRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuOptionResponseDto;
import org.koreait.yumyum.service.MenuOptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.MENU_OPTION)
@RequiredArgsConstructor
public class MenuOptionController {

    private final MenuOptionService menuOptionService;

    public static final String OPTION_PUT_ID = "/{optionId}";
    public static final String OPTION_DELETE_ID = "/{optionId}";

    @PostMapping
    public ResponseEntity<ResponseDto<MenuOptionResponseDto>> addMenuOption(
            @RequestBody MenuOptionRequestDto dto,
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<MenuOptionResponseDto> response = menuOptionService.addMenuOption(dto, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(OPTION_PUT_ID)
    public ResponseEntity<ResponseDto<MenuOptionResponseDto>> updateMenuOption(
            @RequestBody MenuOptionUpdateRequestDto dto,
            @PathVariable Long optionId,
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<MenuOptionResponseDto> response = menuOptionService.updateMenuOption(dto, optionId, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(OPTION_DELETE_ID)
    public ResponseEntity<ResponseDto<Void>> deleteMenuOption(
            @PathVariable Long optionId,
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<Void> response = menuOptionService.deleteMenuOption(optionId, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}

