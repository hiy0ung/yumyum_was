package org.koreait.yumyum.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.category.request.CreateCategoryRequestDto;
import org.koreait.yumyum.dto.category.request.UpdateCategoryRequestDto;
import org.koreait.yumyum.dto.category.response.CategoryResponseDto;
import org.koreait.yumyum.entity.MenuCategory;
import org.koreait.yumyum.entity.Store;
import org.koreait.yumyum.repository.CategoryRepository;
import org.koreait.yumyum.repository.StoreRepository;
import org.koreait.yumyum.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;

    @Override
    public ResponseDto<List<CategoryResponseDto>> getCategories(String userId) {
        List<CategoryResponseDto> data = null;

        try {
            Optional<Store> optionalStore = storeRepository.getStoreByUserId(userId);

            if(optionalStore.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
            }

            Store store = optionalStore.get();

            List<MenuCategory> menuCategoryList = categoryRepository.getMenuCategoryByStoreId(store.getId());

            data = menuCategoryList.stream()
                    .map(CategoryResponseDto::new)
                    .collect(Collectors.toList());

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<CategoryResponseDto> createCategory(String userId, CreateCategoryRequestDto dto) {
        CategoryResponseDto data = null;

        try {
            Optional<Store> optionalStore = storeRepository.getStoreByUserId(userId);

            if(optionalStore.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
            }

            Store store = optionalStore.get();

            MenuCategory menuCategory = MenuCategory.builder()
                    .storeId(store.getId())
                    .menuCategory(dto.getCategoryName())
                    .build();

            categoryRepository.save(menuCategory);

            data = new CategoryResponseDto(menuCategory);

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<CategoryResponseDto> updateCategory(UpdateCategoryRequestDto dto) {
        CategoryResponseDto data = null;
        Long CategoryId = dto.getId();

        try {

            Optional<MenuCategory> optionalMenuCategory = categoryRepository.findById(CategoryId);

            if(optionalMenuCategory.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CATEGORY);
            }

            MenuCategory menuCategory = optionalMenuCategory.get();
            MenuCategory updateMenuCategory = menuCategory.toBuilder()
                    .menuCategory(dto.getCategoryName())
                    .build();
            categoryRepository.save(updateMenuCategory);

            data = new CategoryResponseDto(updateMenuCategory);

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteCategory(Long id) {
        try {
            Optional<MenuCategory> optionalMenuCategory = categoryRepository.findById(id);

            if(optionalMenuCategory.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CATEGORY);
            }

            MenuCategory menuCategory = optionalMenuCategory.get();
            categoryRepository.delete(menuCategory);

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}

