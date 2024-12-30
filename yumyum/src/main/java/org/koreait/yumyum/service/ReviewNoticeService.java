package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.reviewNotice.request.ReviewNoticeRequestDto;
import org.koreait.yumyum.dto.reviewNotice.response.ReviewNoticeResponseDto;

public interface ReviewNoticeService {
    ResponseDto<ReviewNoticeResponseDto> getNotice(Long id);

    ResponseDto<ReviewNoticeResponseDto> createNotice(Long id, ReviewNoticeRequestDto dto);

    ResponseDto<ReviewNoticeResponseDto> updateNotice(Long id, ReviewNoticeRequestDto dto);

    ResponseDto<String> deleteNotice(Long id, Long noticeId);
}
