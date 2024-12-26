package org.koreait.yumyum.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.review.response.ReviewResponseDto;
import org.koreait.yumyum.entity.Store;
import org.koreait.yumyum.repository.ReviewRepository;
import org.koreait.yumyum.repository.StoreRepository;
import org.koreait.yumyum.service.ReviewService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    public ResponseDto<List<ReviewResponseDto>> getAllReviews(Long id) {

        Optional<Store> optionalStore = storeRepository.getStoreByUserId(id);

        if (optionalStore.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
        }

        Store store = optionalStore.get();
        Long storeId = store.getId();

        List<ReviewResponseDto> data;

        try {
            data = reviewRepository.findReviewsByStoreId(storeId).stream()
                    .map(dto -> new ReviewResponseDto(
                            (Long) dto[0],
                            (Double) dto[1],
                            ((Timestamp) dto[2]).toLocalDateTime(),
                            (String) dto[3],
                            (Boolean) dto[4],
                            (String) dto[5],
                            (String) dto[6],
                            ((Timestamp) dto[7]).toLocalDateTime(),
                            (String) dto[8],
                            (String) dto[9],
                            ((Timestamp) dto[10]).toLocalDateTime()
                    )).collect(Collectors.toList());
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
