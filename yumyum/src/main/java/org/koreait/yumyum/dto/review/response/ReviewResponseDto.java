package org.koreait.yumyum.dto.review.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.yumyum.entity.Review;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private Double rating;
    private LocalDateTime reviewDate;
    private String reviewText;
    private List<String> reviewPhotos;
    private Boolean isReported;
    private String guestNickname;
    private String profileImage;
    private LocalDateTime orderDate;
    private List<String> menuNames;
    private String reviewComment;
    private LocalDateTime commentDate;
}
