package org.koreait.yumyum.dto.auth.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserEmailDuplicationCheckResponseDto {
    private Boolean duplicatedStatus;

    public UserEmailDuplicationCheckResponseDto(Boolean duplicatedStatus) {
        this.duplicatedStatus = duplicatedStatus;
    }
}
