package org.koreait.yumyum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ApiMappingPattern;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.auth.request.LoginRequestDto;
import org.koreait.yumyum.dto.auth.request.SignUpRequestDto;
import org.koreait.yumyum.dto.auth.request.UserBusinessNumberDuplicationCheckRequestDto;
import org.koreait.yumyum.dto.auth.request.UserIdDuplicationCheckRequestDto;
import org.koreait.yumyum.dto.auth.response.LoginResponseDto;
import org.koreait.yumyum.dto.auth.response.SignUpResponseDto;
import org.koreait.yumyum.dto.auth.response.UserBusinessNumberDuplicationCheckResponseDto;
import org.koreait.yumyum.dto.auth.response.UserIdDuplicationCheckResponseDto;
import org.koreait.yumyum.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    public static final String SIGN_UP_PATH = "/signUp";
    public static final String SIGN_UP_USER_ID_DUPLICATION_CHECK_PATH = "/signUp/search/userId";
    public static final String SIGN_UP_USER_BUSINESS_NUMBER_DUPLICATION_CHECK_PATH = "/signUp/search/userBusinessNumber";
    public static final String LOGIN_PATH = "/login";

    @PostMapping(SIGN_UP_PATH)
    public ResponseEntity<ResponseDto<SignUpResponseDto>> signUp(@Valid @RequestBody SignUpRequestDto dto) {
        ResponseDto<SignUpResponseDto> response = authService.signUp(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(SIGN_UP_USER_ID_DUPLICATION_CHECK_PATH)
    public ResponseEntity<ResponseDto<UserIdDuplicationCheckResponseDto>> userIdDuplicationCheck(@Valid @RequestBody UserIdDuplicationCheckRequestDto dto) {
        ResponseDto<UserIdDuplicationCheckResponseDto> response = authService.userIdDuplicationCheck(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(SIGN_UP_USER_BUSINESS_NUMBER_DUPLICATION_CHECK_PATH)
    public ResponseEntity<ResponseDto<UserBusinessNumberDuplicationCheckResponseDto>> userBusinessNumberDuplicationCheck(@Valid @RequestBody UserBusinessNumberDuplicationCheckRequestDto dto) {
        ResponseDto<UserBusinessNumberDuplicationCheckResponseDto> response = authService.userBusinessNumberDuplicationCheck(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(LOGIN_PATH)
    public ResponseEntity<ResponseDto<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto dto) {
        ResponseDto<LoginResponseDto> response = authService.login(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(response);
    }

}
