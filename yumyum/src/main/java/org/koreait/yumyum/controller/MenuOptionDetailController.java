package org.koreait.yumyum.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ApiMappingPattern;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionDetailRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuOptionDetailResponseDto;
import org.koreait.yumyum.service.MenuOptionDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.MENU_OPTION_DETAILS)
@RequiredArgsConstructor
public class MenuOptionDetailController {

    private final MenuOptionDetailService menuOptionDetailService;

    public static final String OPTION_DETAIL_POST_ADD = "/add";
    public static final String OPTION_DETAIL_PUT_ID = "/{id}";
    public static final String OPTION_DETAIL_DELETE_ID = "/{id}";

    @PostMapping(OPTION_DETAIL_POST_ADD)
    public ResponseEntity<ResponseDto<MenuOptionDetailResponseDto>> addOptionDetail(@RequestBody MenuOptionDetailRequestDto dto) {
        ResponseDto<MenuOptionDetailResponseDto> response = menuOptionDetailService.addOptionDetail(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(OPTION_DETAIL_PUT_ID)
    public ResponseEntity<ResponseDto<MenuOptionDetailResponseDto>> updateOptionDetail(@RequestBody MenuOptionDetailRequestDto dto, @PathVariable Long id) {
        ResponseDto<MenuOptionDetailResponseDto> response = menuOptionDetailService.updateOptionDetail(dto, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(OPTION_DETAIL_DELETE_ID)
    public ResponseEntity<ResponseDto<Void>> deleteOptionDetail(@PathVariable Long id) {
        ResponseDto<Void> response = menuOptionDetailService.deleteOptionDetail(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
