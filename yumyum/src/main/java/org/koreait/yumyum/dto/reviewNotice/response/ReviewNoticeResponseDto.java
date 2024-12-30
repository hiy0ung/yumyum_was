package org.koreait.yumyum.dto.reviewNotice.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.yumyum.entity.ReviewEventNotice;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewNoticeResponseDto {

    private LocalDateTime noticeDate;

    private String noticePhoto;

    private String noticeText;

    public ReviewNoticeResponseDto(ReviewEventNotice reviewEventNotice) {
        this.noticeDate = reviewEventNotice.getNoticeDate();
        this.noticePhoto = reviewEventNotice.getNoticePhoto();
        this.noticeText = reviewEventNotice.getNoticeText();
    }
}
