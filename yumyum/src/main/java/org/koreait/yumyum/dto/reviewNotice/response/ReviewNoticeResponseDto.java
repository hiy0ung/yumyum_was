package org.koreait.yumyum.dto.reviewNotice.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.yumyum.entity.ReviewEventNotice;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewNoticeResponseDto {

    private LocalDateTime noticeDate;

    private String noticePhotoUrl;

    private String noticeText;

    public ReviewNoticeResponseDto(ReviewEventNotice reviewEventNotice) {
        this.noticeDate = reviewEventNotice.getNoticeDate();
        this.noticePhotoUrl = reviewEventNotice.getNoticePhotoUrl();
        this.noticeText = reviewEventNotice.getNoticeText();
    }
}
