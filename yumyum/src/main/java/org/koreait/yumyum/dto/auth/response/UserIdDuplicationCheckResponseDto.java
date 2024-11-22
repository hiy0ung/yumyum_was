package org.koreait.yumyum.dto.auth.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserIdDuplicationCheckResponseDto {
    private Boolean duplicatedStatus;

    public UserIdDuplicationCheckResponseDto(Boolean duplicatedStatus) {
        this.duplicatedStatus = duplicatedStatus;
    }
}
