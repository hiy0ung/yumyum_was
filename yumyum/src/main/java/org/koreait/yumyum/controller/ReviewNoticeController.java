package org.koreait.yumyum.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ApiMappingPattern;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.reviewNotice.request.ReviewNoticeRequestDto;
import org.koreait.yumyum.dto.reviewNotice.response.ReviewNoticeResponseDto;
import org.koreait.yumyum.service.ReviewNoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.REVIEW_NOTICE)
@RequiredArgsConstructor
public class ReviewNoticeController {

    private final ReviewNoticeService reviewNoticeService;

    private static final String CREATE_NOTICE = "/create";
    private static final String UPDATE_NOTICE = "/update";
    private static final String DELETE_NOTICE = "/delete/{noticeId}";


    @GetMapping()
    public ResponseEntity<ResponseDto<ReviewNoticeResponseDto>> getNotice(@AuthenticationPrincipal Long id) {
        ResponseDto<ReviewNoticeResponseDto> response = reviewNoticeService.getNotice(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(CREATE_NOTICE)
    public ResponseEntity<ResponseDto<ReviewNoticeResponseDto>> createNotice(@AuthenticationPrincipal Long id, @ModelAttribute ReviewNoticeRequestDto dto) {
        ResponseDto<ReviewNoticeResponseDto> response = reviewNoticeService.createNotice(id, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(UPDATE_NOTICE)
    public ResponseEntity<ResponseDto<ReviewNoticeResponseDto>> updateNotice(@AuthenticationPrincipal Long id,@RequestParam Long reviewEventNoticeId, @RequestBody ReviewNoticeRequestDto dto) {
        ResponseDto<ReviewNoticeResponseDto> response = reviewNoticeService.updateNotice(id, reviewEventNoticeId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(DELETE_NOTICE)
    public ResponseEntity<ResponseDto<String>> deleteNotice(@AuthenticationPrincipal Long id, @PathVariable Long noticeId) {
        ResponseDto<String> response = reviewNoticeService.deleteNotice(id, noticeId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
