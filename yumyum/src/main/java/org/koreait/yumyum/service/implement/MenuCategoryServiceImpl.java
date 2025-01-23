package org.koreait.yumyum.service.implement;

import org.koreait.yumyum.entity.Store;
import org.koreait.yumyum.repository.StoreRepository;
import org.koreait.yumyum.service.MenuCategoryService;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuCategoryRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuCategoryResponseDto;
import org.koreait.yumyum.entity.MenuCategory;
import org.koreait.yumyum.repository.MenuCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuCategoryServiceImpl implements MenuCategoryService {

    private final MenuCategoryRepository menuCategoryRepository;
    private final StoreRepository storeRepository;

    @Override
    public ResponseDto<List<MenuCategoryResponseDto>> getAllMenuCategory(Long id) {
        List<MenuCategoryResponseDto> data = null;
        try {
            Store store = storeRepository.getStoreByUserId(id)
                    .orElseThrow(() -> new RuntimeException(ResponseMessage.NOT_EXIST_STORE));
            List<MenuCategory> categories = menuCategoryRepository.findAllCategoryByStoreId(store.getId());
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
    public ResponseDto<MenuCategoryResponseDto> createCategory(Long id, MenuCategoryRequestDto dto) {
        MenuCategoryResponseDto data = null;
        try {
            Store store = storeRepository.getStoreByUserId(id)
                    .orElseThrow(() -> new Exception(ResponseMessage.NOT_EXIST_DATA));
            MenuCategory menuCategory = MenuCategory.builder()
                    .menuCategory(dto.getMenuCategory())
                    .menuCategorySequence(dto.getMenuCategorySequence())
                    .store(store)
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

    @Override
    public ResponseDto<Void> deleteCategory(Long categoryId) {
        try {
            MenuCategory category = menuCategoryRepository.findById(categoryId)
                    .orElseThrow(() -> new Error(ResponseMessage.NOT_EXIST_CATEGORY));
            menuCategoryRepository.deleteById(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}