package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.password.ChangePasswordRequestDto;

public interface PasswordService {
    ResponseDto<String> changePassword(ChangePasswordRequestDto dto);
}