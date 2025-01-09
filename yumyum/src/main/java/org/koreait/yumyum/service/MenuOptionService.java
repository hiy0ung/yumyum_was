package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuOptionResponseDto;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public interface MenuOptionService {
    ResponseDto<MenuOptionResponseDto> addMenuOption(MenuOptionRequestDto dto, Long id);

    ResponseDto<MenuOptionResponseDto> updateMenuOption(MenuOptionRequestDto dto, Long optionId, Long id);

    ResponseDto<Void> deleteMenuOption(Long optionId, Long id);
}
