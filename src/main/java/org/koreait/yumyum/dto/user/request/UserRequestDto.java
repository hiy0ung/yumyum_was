package org.koreait.yumyum.dto.user.request;

import lombok.Data;

@Data
public class UserRequestDto {
    private String userPw;
    private String userEmail;
    private String userPhone;
    private Boolean marketingAgreed;
}
