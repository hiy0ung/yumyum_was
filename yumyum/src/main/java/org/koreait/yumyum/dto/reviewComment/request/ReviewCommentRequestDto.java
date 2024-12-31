package org.koreait.yumyum.dto.reviewComment.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewCommentRequestDto {

    @NotNull
    private String commentText;

    @NotNull
    private LocalDateTime commentDate;
}
