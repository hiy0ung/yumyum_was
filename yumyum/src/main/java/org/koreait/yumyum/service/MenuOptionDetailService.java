package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionDetailRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuOptionDetailResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuOptionDetailService {
    ResponseDto<MenuOptionDetailResponseDto> addOptionDetail(MenuOptionDetailRequestDto dto);

    ResponseDto<List<MenuOptionDetailResponseDto>> getAllOptionDetails();

    ResponseDto<MenuOptionDetailResponseDto> getOptionDetailById(Long id);

    ResponseDto<MenuOptionDetailResponseDto> updateOptionDetail(MenuOptionDetailRequestDto dto, Long id);

    ResponseDto<Void> deleteOptionDetail(Long id);
}
