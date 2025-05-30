package org.koreait.yumyum.dto.auth.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserBusinessNumberDuplicationCheckResponseDto {
    private Boolean duplicatedStatus;

    public UserBusinessNumberDuplicationCheckResponseDto(Boolean duplicatedStatus) {
        this.duplicatedStatus = duplicatedStatus;
    }
}
