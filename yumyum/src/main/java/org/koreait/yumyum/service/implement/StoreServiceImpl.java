package org.koreait.yumyum.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.store.request.StoreRequestDto;
import org.koreait.yumyum.dto.store.response.StoreResponseDto;
import org.koreait.yumyum.entity.Store;
import org.koreait.yumyum.entity.User;
import org.koreait.yumyum.repository.StoreRepository;
import org.koreait.yumyum.repository.UserRepository;
import org.koreait.yumyum.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {


    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<StoreResponseDto> getStore(String userId) {
        StoreResponseDto data = null;

        try {
            Optional<Store> optionalStore = storeRepository.getStoreByUserId(userId);

            if (optionalStore.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
            }

            Store store = optionalStore.get();
            data = new StoreResponseDto(store);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<StoreResponseDto> createStore(String userId, StoreRequestDto dto) {
        StoreResponseDto data = null;

        try {
            Optional<User> optionalUser = userRepository.findByUserId(userId);

            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            User user = optionalUser.get();

            Store store = Store.builder()
                    .user(user)
                    .storeName(dto.getStoreName())
                    .logoUrl(dto.getLogoUrl())
                    .category(dto.getCategory())
                    .openingTime(dto.getOpeningTime())
                    .closingTime(dto.getClosingTime())
                    .breakStartTime(dto.getBreakStartTime())
                    .breakEndTime(dto.getBreakEndTime())
                    .address(dto.getAddress())
                    .description(dto.getDescription())
                    .build();
            storeRepository.save(store);

            data = new StoreResponseDto(store);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    @Override
    public ResponseDto<StoreResponseDto> updateStore(String userId, StoreRequestDto dto) {
        StoreResponseDto data = null;

        try {
            Optional<Store> optionalStore = storeRepository.getStoreByUserId(userId);

            if (optionalStore.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
            }

            Store store = optionalStore.get();
            Store updateStore = store.toBuilder()
                    .storeName(dto.getStoreName())
                    .logoUrl(dto.getLogoUrl())
                    .category(dto.getCategory())
                    .openingTime(dto.getOpeningTime())
                    .closingTime(dto.getClosingTime())
                    .breakStartTime(dto.getBreakStartTime())
                    .breakEndTime(dto.getBreakEndTime())
                    .address(dto.getAddress())
                    .description(dto.getDescription())
                    .build();
            storeRepository.save(updateStore);

            data = new StoreResponseDto(store);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteStore(String userId) {
        try {
            Optional<Store> optionalStore = storeRepository.getStoreByUserId(userId);

            if (optionalStore.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
            }

            Store store = optionalStore.get();
            storeRepository.delete(store);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}

