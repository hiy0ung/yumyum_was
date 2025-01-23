package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionDetailRequestDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionDetailUpdateRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuOptionDetailResponseDto;

import java.util.List;

public interface MenuOptionDetailService {
    ResponseDto<MenuOptionDetailResponseDto> addOptionDetail(MenuOptionDetailRequestDto dto, Long id);
    ResponseDto<MenuOptionDetailResponseDto> updateOptionDetail(MenuOptionDetailUpdateRequestDto dto, Long optionDetailId,Long pkId, Long id);
    ResponseDto<Void> deleteOptionDetail(Long optionDetailId, Long id);
}
