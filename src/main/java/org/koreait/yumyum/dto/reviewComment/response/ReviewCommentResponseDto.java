package org.koreait.yumyum.dto.reviewComment.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.yumyum.entity.ReviewComment;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewCommentResponseDto {
    private Long id;
    private String commentText;
    private LocalDateTime commentDate;

    public ReviewCommentResponseDto(ReviewComment reviewComment) {
        this.id = reviewComment.getId();
        this.commentText = reviewComment.getComment();
        this.commentDate = reviewComment.getCommentDate();
    }
}
