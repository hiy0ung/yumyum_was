package org.koreait.yumyum.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ApiMappingPattern;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionDetailRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuOptionDetailResponseDto;
import org.koreait.yumyum.service.MenuOptionDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.MENU_OPTION_DETAILS)
@RequiredArgsConstructor
public class MenuOptionDetailController {

    private final MenuOptionDetailService menuOptionDetailService;

    public static final String OPTION_DETAIL_POST_ADD = "/add";
    public static final String OPTION_DETAIL_PUT_ID = "/update/{optionDetailId}";
    public static final String OPTION_DETAIL_DELETE_ID = "/delete/{optionDetailId}";

    @PostMapping(OPTION_DETAIL_POST_ADD)
    public ResponseEntity<ResponseDto<MenuOptionDetailResponseDto>> addOptionDetail(
            @RequestBody MenuOptionDetailRequestDto dto,
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<MenuOptionDetailResponseDto> response = menuOptionDetailService.addOptionDetail(dto, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(OPTION_DETAIL_PUT_ID)
    public ResponseEntity<ResponseDto<MenuOptionDetailResponseDto>> updateOptionDetail(
            @RequestBody MenuOptionDetailRequestDto dto,
            @PathVariable Long optionDetailId,
            @AuthenticationPrincipal Long id
            ) {
        ResponseDto<MenuOptionDetailResponseDto> response = menuOptionDetailService.updateOptionDetail(dto, optionDetailId, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(OPTION_DETAIL_DELETE_ID)
    public ResponseEntity<ResponseDto<Void>> deleteOptionDetail(
            @PathVariable Long optionDetailId,
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<Void> response = menuOptionDetailService.deleteOptionDetail(optionDetailId, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
