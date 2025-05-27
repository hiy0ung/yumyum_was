package org.koreait.yumyum.dto.auth.response;

import lombok.Getter;
import org.koreait.yumyum.entity.User;

@Getter
public class SignUpResponseDto {
    private String userName;

    public SignUpResponseDto(User user) {
        this.userName = user.getUserName();
    }
}