package org.koreait.yumyum.dto.review.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private Long id;                        // 리뷰 ID
    private Double rating;                  // 별점
    private LocalDateTime reviewDate;       // 리뷰 작성 날짜
    private String reviewText;              // 리뷰 내용
    private Boolean isReported;             // 신고 여부
    private String guestNickname;           // 게스트 닉네임
    private String profileImage;            // 게스트 프로필 이미지
    private LocalDateTime orderDate;        // 주문 날짜
    private String menuNames;               // 메뉴 이름 목록
    private String reviewComment;           // 리뷰 댓글
    private LocalDateTime commentDate;      // 댓글 작성 날짜
}
