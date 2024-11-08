package org.koreait.yumyum.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.koreait.yumyum.entity.User;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private User user;
    private String token;
    private int exprTime;
}
