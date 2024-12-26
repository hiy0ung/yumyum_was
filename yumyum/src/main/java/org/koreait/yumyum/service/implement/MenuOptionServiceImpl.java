package org.koreait.yumyum.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionDetailRequestDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuOptionResponseDto;
import org.koreait.yumyum.entity.Menu;
import org.koreait.yumyum.entity.MenuOption;
import org.koreait.yumyum.entity.MenuOptionGroup;
import org.koreait.yumyum.repository.MenuOptionGroupRepository;
import org.koreait.yumyum.repository.MenuOptionRepository;
import org.koreait.yumyum.repository.MenuRepository;
import org.koreait.yumyum.service.MenuOptionDetailService;
import org.koreait.yumyum.service.MenuOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuOptionServiceImpl implements MenuOptionService {

    private final MenuOptionRepository menuOptionRepository;

    private final MenuRepository menuRepository;

    private final MenuOptionGroupRepository menuOptionGroupRepository;

    @Autowired
    private final MenuOptionDetailService menuOptionDetailService;

    @Override
    public ResponseDto<MenuOptionResponseDto> addMenuOption(MenuOptionRequestDto dto) {
        MenuOptionResponseDto data = null;

        try {
            Menu menu = menuRepository.findById(dto.getMenuId())
                    .orElseThrow(() -> new Error(ResponseMessage.NOT_EXIST_DATA));

            MenuOption menuOption = MenuOption.builder()
                    .optionName(dto.getOptionName())
                    .build();

            MenuOption savedMenuOption = menuOptionRepository.save(menuOption);
            List<MenuOptionDetailRequestDto> details = dto.getOptionDetail();
            if(details != null) {
                for (MenuOptionDetailRequestDto detailDto : details) {
                    detailDto.setMenuOptionId(savedMenuOption.getId());
                    menuOptionDetailService.addOptionDetail(detailDto);
                }

            }
            MenuOptionGroup menuOptionGroup = MenuOptionGroup.builder()
                    .menu(menu)
                    .menuOption(menuOption)
                    .build();

            menuOptionGroupRepository.save(menuOptionGroup);

            data = new MenuOptionResponseDto(savedMenuOption);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<MenuOptionResponseDto> updateMenuOption(MenuOptionRequestDto dto, Long id) {
        MenuOptionResponseDto data = null;
        Long menuOptionId = id;

        try {
            Optional<MenuOption> menuOptionOptional = menuOptionRepository.findById(menuOptionId);

            if (menuOptionOptional.isPresent()) {
                MenuOption menuOption = menuOptionOptional.get().toBuilder()
                        .optionName(dto.getOptionName())
                        .build();

                MenuOption updateOption = menuOptionRepository.save(menuOption);
                data = new MenuOptionResponseDto(updateOption);
            } else {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteMenuOption(Long id) {
        Long menuOptionId = id;
        try {
            Optional<MenuOption> menuOptionOptional = menuOptionRepository.findById(menuOptionId);

            if (menuOptionOptional.isPresent()) {
                MenuOption menuOption = menuOptionOptional.get();
                menuOptionRepository.delete(menuOption);
            } else {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}

