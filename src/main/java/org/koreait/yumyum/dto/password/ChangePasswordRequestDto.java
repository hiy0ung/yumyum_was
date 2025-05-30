package org.koreait.yumyum.dto.password;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangePasswordRequestDto {
    @NotNull
    private String token;            // 이메일 인증 토큰
    @NotNull
    private String newPassword;      // 새 비밀번호
    @NotNull
    private String confirmPassword;  // 비밀번호 확인
}
