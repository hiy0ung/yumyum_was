package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.user.request.UserRequestDto;
import org.koreait.yumyum.dto.user.response.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface MyPageService {
    ResponseDto<UserResponseDto> getAllInfo(Long id);

    ResponseDto<UserResponseDto> updateUserInfo(Long id, UserRequestDto dto);

    ResponseDto<Void> deleteUserInfo(Long id);
}
