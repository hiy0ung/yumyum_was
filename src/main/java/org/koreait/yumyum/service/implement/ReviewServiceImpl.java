package org.koreait.yumyum.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.review.response.ReviewResponseDto;
import org.koreait.yumyum.dto.reviewComment.request.ReviewCommentRequestDto;
import org.koreait.yumyum.dto.reviewComment.response.ReviewCommentResponseDto;
import org.koreait.yumyum.dto.reviewNotice.request.ReviewNoticeRequestDto;
import org.koreait.yumyum.entity.Review;
import org.koreait.yumyum.entity.ReviewComment;
import org.koreait.yumyum.entity.Store;
import org.koreait.yumyum.repository.ReviewCommentRepository;
import org.koreait.yumyum.repository.ReviewRepository;
import org.koreait.yumyum.repository.StoreRepository;
import org.koreait.yumyum.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final StoreRepository storeRepository;

    @Override
    public ResponseDto<List<ReviewResponseDto>> getAllReviews(Long id) {
        List<ReviewResponseDto> data;

        try {
            Optional<Store> optionalStore = storeRepository.getStoreByUserId(id);

            if (optionalStore.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
            }

            Long storeId = optionalStore.get().getId();

            data = reviewRepository.findReviewsByStoreId(storeId).stream()
                    .map(dto -> new ReviewResponseDto(
                            (Long) dto[0],
                            (Double) dto[1],
                            ((Timestamp) dto[2]).toLocalDateTime(),
                            (String) dto[3],
                            convertToList((String) dto[4]),
                            (Boolean) dto[5],
                            (String) dto[6],
                            (String) dto[7],
                            ((Timestamp) dto[8]).toLocalDateTime(),
                            convertToList((String) dto[9]),
                            dto[10] !=null ? (String) dto[10] : null,
                            dto[11] !=null ? ((Timestamp) dto[11]).toLocalDateTime() : null
                    )).collect(Collectors.toList());
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    private List<String> convertToList(String str) {
        if(str == null || str.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(str.split(", "));
    }

    @Override
    public ResponseDto<List<ReviewResponseDto>> getUnansweredReviews(Long id) {
        List<ReviewResponseDto> data = null;

        try {
            Optional<Store> optionalStore = storeRepository.getStoreByUserId(id);

            if (optionalStore.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
            }

            Long storeId = optionalStore.get().getId();

            data = reviewRepository.findReviewsByStoreId(storeId).stream()
                    .filter(dto -> dto[10] == null && dto[11] == null)
                    .map(dto -> new ReviewResponseDto(
                            (Long) dto[0],
                            (Double) dto[1],
                            ((Timestamp) dto[2]).toLocalDateTime(),
                            (String) dto[3],
                            convertToList((String) dto[4]),
                            (Boolean) dto[5],
                            (String) dto[6],
                            (String) dto[7],
                            ((Timestamp) dto[8]).toLocalDateTime(),
                            convertToList((String) dto[9]),
                            dto[10] !=null ? (String) dto[10] : null,
                            dto[11] !=null ? ((Timestamp) dto[11]).toLocalDateTime() : null
                    )).collect(Collectors.toList());
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<ReviewCommentResponseDto> createReviewComment(Long id, Long reviewId, ReviewCommentRequestDto dto) {
        ReviewCommentResponseDto data = null;

        try {
            Optional<Review> optionalReview = reviewRepository.findById(reviewId);
            if(optionalReview.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_REVIEW);
            }

            Optional<ReviewComment> optionalReviewComment = reviewCommentRepository.findReviewCommentByReviewId(reviewId);
            if(optionalReviewComment.isPresent()) {
                return ResponseDto.setFailed(ResponseMessage.REVIEW_COMMENT_ALREADY_EXISTS);
            }

            Review review = optionalReview.get();

            ReviewComment reviewComment = ReviewComment.builder()
                    .review(review)
                    .comment(dto.getCommentText())
                    .commentDate(dto.getCommentDate())
                    .build();
            reviewCommentRepository.save(reviewComment);

            data = new ReviewCommentResponseDto(reviewComment);

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<String> deleteReviewComment(Long id, Long reviewId) {
        try {

            Optional<Review> optionalReview = reviewRepository.findById(reviewId);
            if(optionalReview.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_REVIEW);
            }
            Review review = optionalReview.get();

            Optional<ReviewComment> optionalReviewComment = reviewCommentRepository.findReviewCommentByReviewId(review.getId());
            if(optionalReviewComment.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_REVIEW_COMMENT);
            }

            ReviewComment reviewComment = optionalReviewComment.get();
            reviewCommentRepository.delete(reviewComment);

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, "답글 삭제에 성공하였습니다.");
    }

}
