package org.koreait.yumyum.service;

import jakarta.validation.Valid;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.store.request.StoreRequestDto;
import org.koreait.yumyum.dto.store.response.StoreResponseDto;

public interface StoreService {
    ResponseDto<StoreResponseDto> getStore(String userId);

    ResponseDto<StoreResponseDto> createStore(String userId, @Valid StoreRequestDto dto);

    ResponseDto<StoreResponseDto> updateStore(String userId, @Valid StoreRequestDto dto);

    ResponseDto<Void> deleteStore(String userId);
}
