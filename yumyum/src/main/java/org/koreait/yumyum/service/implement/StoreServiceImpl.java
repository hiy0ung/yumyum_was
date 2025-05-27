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
    public boolean findByStore(Long id) {
        try {
            Optional<Store> optionalStore = storeRepository.getStoreByUserId(id);
            if(optionalStore.isEmpty()) {
                ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public ResponseDto<StoreResponseDto> getStore(Long id) {
        StoreResponseDto data = null;

        try {
            Optional<Store> optionalStore = storeRepository.getStoreByUserId(id);

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
    public ResponseDto<StoreResponseDto> createStore(Long id, StoreRequestDto dto) {
        StoreResponseDto data = null;

        try {
            Optional<User> optionalUser = userRepository.findById(id);

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
                    .zoneCode(dto.getZoneCode())
                    .address(dto.getAddress())
                    .detailAddress(dto.getDetailAddress())
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
    public ResponseDto<StoreResponseDto> updateStore(Long id, StoreRequestDto dto) {
        StoreResponseDto data = null;

        try {
            Optional<Store> optionalStore = storeRepository.getStoreByUserId(id);

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
                    .zoneCode(dto.getZoneCode())
                    .address(dto.getAddress())
                    .detailAddress(dto.getDetailAddress())
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
    public ResponseDto<String> deleteStore(Long id) {
        try {
            Optional<Store> optionalStore = storeRepository.getStoreByUserId(id);

            if (optionalStore.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
            }

            Store store = optionalStore.get();
            storeRepository.delete(store);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, "가게 삭제에 성공하였습니다.");
    }
}

