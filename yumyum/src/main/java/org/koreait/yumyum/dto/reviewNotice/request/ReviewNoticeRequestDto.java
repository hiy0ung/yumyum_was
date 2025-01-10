package org.koreait.yumyum.dto.reviewNotice.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewNoticeRequestDto {
    @NotNull
    private LocalDateTime noticeDate;

    private String noticePhotoUrl;

    private String noticeText;
}
