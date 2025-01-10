package org.koreait.yumyum.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.reviewNotice.request.ReviewNoticeRequestDto;
import org.koreait.yumyum.dto.reviewNotice.response.ReviewNoticeResponseDto;
import org.koreait.yumyum.entity.ReviewEventNotice;
import org.koreait.yumyum.entity.Store;
import org.koreait.yumyum.repository.ReviewNoticeRepository;
import org.koreait.yumyum.repository.StoreRepository;
import org.koreait.yumyum.service.ReviewNoticeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewNoticeServiceImpl implements ReviewNoticeService {

    private final ReviewNoticeRepository reviewNoticeRepository;
    private final StoreRepository storeRepository;


    @Override
    public ResponseDto<ReviewNoticeResponseDto> getNotice(Long id) {
        ReviewNoticeResponseDto data = null;

        try {
            Optional<Store> optionalStore = storeRepository.getStoreByUserId(id);
            if(optionalStore.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
            }

            Long storeId = optionalStore.get().getId();

            Optional<ReviewEventNotice> optionalReviewEventNotice = reviewNoticeRepository.getReviewEventNoticeByStoreId(storeId);

            if(optionalReviewEventNotice.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_REVIEW_NOTICE);
            }

            ReviewEventNotice reviewEventNotice = optionalReviewEventNotice.get();
            data = new ReviewNoticeResponseDto(reviewEventNotice);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<ReviewNoticeResponseDto> createNotice(Long id, ReviewNoticeRequestDto dto) {
        ReviewNoticeResponseDto data = null;

        try {
            Optional<Store> optionalStore = storeRepository.getStoreByUserId(id);
            if(optionalStore.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
            }

            Long storeId = optionalStore.get().getId();

            Optional<ReviewEventNotice> optionalReviewEventNotice = reviewNoticeRepository.getReviewEventNoticeByStoreId(storeId);

            if(optionalReviewEventNotice.isPresent()) {
                return ResponseDto.setFailed(ResponseMessage.REVIEW_NOTICE_ALREADY_EXISTS);
            }

            Store store = optionalStore.get();

            ReviewEventNotice reviewEventNotice = ReviewEventNotice.builder()
                    .store(store)
                    .noticeDate(dto.getNoticeDate())
                    .noticePhotoUrl(dto.getNoticePhotoUrl())
                    .noticeText(dto.getNoticeText())
                    .build();
            reviewNoticeRepository.save(reviewEventNotice);

            data = new ReviewNoticeResponseDto(reviewEventNotice);

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<ReviewNoticeResponseDto> updateNotice(Long id,Long noticeId, ReviewNoticeRequestDto dto) {
        ReviewNoticeResponseDto data = null;

        try {
            Optional<ReviewEventNotice> optionalReviewEventNotice = reviewNoticeRepository.findById(noticeId);

            if(optionalReviewEventNotice.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_REVIEW_NOTICE);
            }

            ReviewEventNotice reviewEventNotice = optionalReviewEventNotice.get();
            ReviewEventNotice updateReviewEventNotice = reviewEventNotice.toBuilder()
                    .noticeDate(dto.getNoticeDate())
                    .noticePhotoUrl(dto.getNoticePhotoUrl())
                    .noticeText(dto.getNoticeText())
                    .build();
            reviewNoticeRepository.save(updateReviewEventNotice);

            data = new ReviewNoticeResponseDto(reviewEventNotice);

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<String> deleteNotice(Long id, Long noticeId) {
        try {
            Optional<ReviewEventNotice> optionalReviewEventNotice = reviewNoticeRepository.findById(noticeId);

            if(optionalReviewEventNotice.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_REVIEW_NOTICE);
            }

            ReviewEventNotice reviewEventNotice = optionalReviewEventNotice.get();
            reviewNoticeRepository.delete(reviewEventNotice);

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, "리뷰이벤트 삭제에 성공하였습니다.");
    }
}
