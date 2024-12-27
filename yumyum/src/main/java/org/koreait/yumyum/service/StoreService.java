package org.koreait.yumyum.service;

import jakarta.validation.Valid;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.store.request.StoreRequestDto;
import org.koreait.yumyum.dto.store.response.StoreResponseDto;

public interface StoreService {

    boolean findByStore(Long id);

    ResponseDto<StoreResponseDto> getStore(Long id);

    ResponseDto<StoreResponseDto> createStore(Long id, @Valid StoreRequestDto dto);

    ResponseDto<StoreResponseDto> updateStore(Long id, @Valid StoreRequestDto dto);

    ResponseDto<String> deleteStore(Long id);
}
