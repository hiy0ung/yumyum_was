package org.koreait.yumyum.dto.auth.response;

import lombok.Getter;
import org.koreait.yumyum.entity.User;

@Getter
public class SignUpResponseDto {
    private Long id;
    private String userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userBusinessNumber;
    private boolean marketingAgreed;

    public SignUpResponseDto(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userPhone = user.getUserPhone();
        this.userBusinessNumber = user.getUserBusinessNumber();
        this.marketingAgreed = user.isMarketingAgreed();
    }
}