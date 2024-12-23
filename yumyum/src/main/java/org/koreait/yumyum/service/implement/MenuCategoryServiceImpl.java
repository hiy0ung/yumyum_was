package org.koreait.yumyum.service.implement;


import lombok.Data;
import org.koreait.yumyum.service.MenuCategoryService;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuCategoryRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuCategoryResponseDto;
import org.koreait.yumyum.entity.MenuCategory;
import org.koreait.yumyum.repository.MenuCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuCategoryServiceImpl implements MenuCategoryService {

    @Autowired
    private final MenuCategoryRepository menuCategoryRepository;


    @Override
    public ResponseDto<List<MenuCategoryResponseDto>> getAllMenuCategory() {
        List<MenuCategoryResponseDto> data = null;

        try {
            List<MenuCategory> categories = menuCategoryRepository.findAll();
            data = new ArrayList<>();

            for(MenuCategory category : categories) {
                MenuCategoryResponseDto dto = new MenuCategoryResponseDto();
                dto.setId(category.getId());
                dto.setMenuCategory(category.getMenuCategory());
                dto.setMenuCategorySequence(category.getMenuCategorySequence());
                data.add(dto);
            }
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<MenuCategoryResponseDto>> updateSequenceCategory(MenuCategoryRequestDto dto) {
        List<MenuCategoryResponseDto> data = null;
        try {
            MenuCategory menuCategory = menuCategoryRepository.findById(dto.getId())
                    .orElseThrow(() -> new Exception(ResponseMessage.DATABASE_ERROR));

            int oldSequence = menuCategory.getMenuCategorySequence();
            int newSequence = dto.getMenuCategorySequence();

            if(oldSequence != newSequence) {
                menuCategory.setMenuCategorySequence(Integer.MAX_VALUE);
                menuCategoryRepository.save(menuCategory);


                if (oldSequence < newSequence) {
                    List<MenuCategory> categories = menuCategoryRepository.findByMenuCategorySequenceBetween(oldSequence + 1, newSequence);
                    for (MenuCategory category : categories) {
                        category.setMenuCategorySequence(category.getMenuCategorySequence() - 1);
                        menuCategoryRepository.save(menuCategory);
                    }
                } else {
                    List<MenuCategory> categories = menuCategoryRepository.findByMenuCategorySequenceBetween(newSequence, oldSequence - 1);
                    for (MenuCategory category : categories) {
                        category.setMenuCategorySequence(category.getMenuCategorySequence() + 1);
                        menuCategoryRepository.save(menuCategory);
                    }
                }

                menuCategory.setMenuCategorySequence(newSequence);
                menuCategoryRepository.save(menuCategory);
            }

            List<MenuCategory> categories = menuCategoryRepository.findAllByOrderByMenuCategorySequenceAsc();
            data = new ArrayList<>();

            for (MenuCategory category : categories) {
                MenuCategoryResponseDto responseDto = new MenuCategoryResponseDto();
                responseDto.setId(category.getId());
                responseDto.setMenuCategory(category.getMenuCategory());
                responseDto.setMenuCategorySequence(category.getMenuCategorySequence());
                data.add(responseDto);
            }

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    public ResponseDto<MenuCategoryResponseDto> createCategory(MenuCategoryRequestDto dto) {
        MenuCategoryResponseDto data = null;
        try {
            MenuCategory menuCategory = MenuCategory.builder()
                    .menuCategory(dto.getMenuCategory())
                    .menuCategorySequence(dto.getMenuCategorySequence())
                    .build();
            MenuCategory savedCategory = menuCategoryRepository.save(menuCategory);

            MenuCategoryResponseDto responseDto = new MenuCategoryResponseDto(savedCategory);
            data = responseDto;

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
