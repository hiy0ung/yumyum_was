package org.koreait.yumyum.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserBusinessNumberDuplicationCheckRequestDto {
    @NotBlank
    private String userBusinessNumber;
}
