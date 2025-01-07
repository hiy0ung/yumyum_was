package org.koreait.yumyum.dto.mail;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMailRequestDto {
    @NotNull
    private String userId;
    @NotNull
    private String userName;
    @NotNull
    private String userEmail;
}
