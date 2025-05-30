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

    @GetMapping()
    public Boolean findByStore(@AuthenticationPrincipal Long id) {
        boolean response = storeService.findByStore(id);
        return response;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseDto<StoreResponseDto>> getStore(@AuthenticationPrincipal Long id) {
        ResponseDto<StoreResponseDto> response = storeService.getStore(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<StoreResponseDto>> createStore(@AuthenticationPrincipal Long id, @Valid @ModelAttribute StoreRequestDto dto) {
        System.out.println(id);
        ResponseDto<StoreResponseDto> response = storeService.createStore(id, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping()
    public ResponseEntity<ResponseDto<StoreResponseDto>> updateStore(@AuthenticationPrincipal Long id, @Valid @ModelAttribute StoreRequestDto dto) {
        ResponseDto<StoreResponseDto> response = storeService.updateStore(id, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDto<String>> deleteStore(@AuthenticationPrincipal Long id) {
        ResponseDto<String> response = storeService.deleteStore(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
