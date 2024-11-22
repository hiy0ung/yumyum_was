package org.koreait.yumyum.service;

import jakarta.validation.Valid;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.auth.request.LoginRequestDto;
import org.koreait.yumyum.dto.auth.request.SignUpRequestDto;
import org.koreait.yumyum.dto.auth.request.UserBusinessNumberDuplicationCheckRequestDto;
import org.koreait.yumyum.dto.auth.request.UserIdDuplicationCheckRequestDto;
import org.koreait.yumyum.dto.auth.response.LoginResponseDto;
import org.koreait.yumyum.dto.auth.response.SignUpResponseDto;
import org.koreait.yumyum.dto.auth.response.UserBusinessNumberDuplicationCheckResponseDto;
import org.koreait.yumyum.dto.auth.response.UserIdDuplicationCheckResponseDto;

public interface AuthService {
    ResponseDto<SignUpResponseDto> signUp(@Valid SignUpRequestDto dto);
    ResponseDto<UserIdDuplicationCheckResponseDto> userIdDuplicationCheck(@Valid UserIdDuplicationCheckRequestDto dto);
    ResponseDto<UserBusinessNumberDuplicationCheckResponseDto> userBusinessNumberDuplicationCheck(@Valid UserBusinessNumberDuplicationCheckRequestDto dto);
    ResponseDto<LoginResponseDto> login(@Valid LoginRequestDto dto);
}
