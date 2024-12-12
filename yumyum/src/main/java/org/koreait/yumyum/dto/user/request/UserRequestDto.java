package org.koreait.yumyum.dto.user.request;

import lombok.Data;

@Data
public class UserRequestDto {
    private Long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userBusinessNumber;
    private Boolean privacyPolicyAgreed;
    private Boolean marketingAgreed;
}
