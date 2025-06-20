package org.koreait.yumyum.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpRequestDto {
    @NotBlank
    private String userId;

    @NotBlank
    private String userPw;

    @NotBlank
    private String checkPw;

    @NotBlank
    private String userName;

    @NotBlank
    private String userEmail;

    @NotBlank
    private String userPhone;

    @NotBlank
    private String userBusinessNumber;

    @NotBlank
    private Boolean privacyPolicyAgreed;

    @NotBlank
    private Boolean marketingAgreed;

    @NotBlank
    @Pattern(regexp = "^(home|kakao|naver)$")
    private String joinPath;

    private String snsId;
}

