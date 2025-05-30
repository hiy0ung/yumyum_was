package org.koreait.yumyum.service;

import jakarta.validation.Valid;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.auth.request.*;
import org.koreait.yumyum.dto.auth.response.*;

public interface AuthService {
    ResponseDto<SignUpResponseDto> signUp(@Valid SignUpRequestDto dto);
    ResponseDto<UserIdDuplicationCheckResponseDto> userIdDuplicationCheck(@Valid UserIdDuplicationCheckRequestDto dto);
    ResponseDto<UserBusinessNumberDuplicationCheckResponseDto> userBusinessNumberDuplicationCheck(@Valid UserBusinessNumberDuplicationCheckRequestDto dto);
    ResponseDto<LoginResponseDto> login(@Valid LoginRequestDto dto);
    ResponseDto<UserEmailDuplicationCheckResponseDto> userEmailDuplicationCheck(@Valid UserEmailDuplicationCheckRequestDto dto);
}
