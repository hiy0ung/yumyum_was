package org.koreait.yumyum.service;

import jakarta.validation.Valid;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.auth.request.LoginRequestDto;
import org.koreait.yumyum.dto.auth.request.SignUpRequestDto;
import org.koreait.yumyum.dto.auth.response.LoginResponseDto;
import org.koreait.yumyum.dto.auth.response.SignUpResponseDto;

public interface AuthService {
    ResponseDto<SignUpResponseDto> signUp(@Valid SignUpRequestDto dto);

    ResponseDto<LoginResponseDto> login(@Valid LoginRequestDto dto);
}
