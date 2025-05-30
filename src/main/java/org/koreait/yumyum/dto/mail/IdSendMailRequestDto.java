package org.koreait.yumyum.dto.mail;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdSendMailRequestDto {
    @NotNull
    private String userName;
    @NotNull
    private String userEmail;

}
