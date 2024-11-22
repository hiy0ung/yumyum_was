package org.koreait.yumyum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ApiMappingPattern;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.store.request.StoreRequestDto;
import org.koreait.yumyum.dto.store.response.StoreResponseDto;
import org.koreait.yumyum.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.STORE)
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    private static final String CREATE_STORE = "/create";
    private static final String UPDATE_STORE = "/update";
    private static final String DELETE_STORE = "/delete";

    @GetMapping()
    public ResponseEntity<ResponseDto<StoreResponseDto>> getStore(@AuthenticationPrincipal String userId) {
        ResponseDto<StoreResponseDto> response = storeService.getStore(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(CREATE_STORE)
    public ResponseEntity<ResponseDto<StoreResponseDto>> createStore(@AuthenticationPrincipal String userId, @Valid @RequestBody StoreRequestDto dto) {
        ResponseDto<StoreResponseDto> response = storeService.createStore(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(UPDATE_STORE)
    public ResponseEntity<ResponseDto<StoreResponseDto>> updateStore(@AuthenticationPrincipal String userId, @Valid @RequestBody StoreRequestDto dto) {
        ResponseDto<StoreResponseDto> response = storeService.updateStore(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(DELETE_STORE)
    public ResponseEntity<ResponseDto<Void>> deleteStore(@AuthenticationPrincipal String userId) {
        ResponseDto<Void> response = storeService.deleteStore(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(null);
    }
}

