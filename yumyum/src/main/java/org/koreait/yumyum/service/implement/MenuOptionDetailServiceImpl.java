package org.koreait.yumyum.service.implement;

import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.generic.ClassGen;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionDetailNameRequestDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionDetailRequestDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionDetailUpdateRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuOptionDetailResponseDto;
import org.koreait.yumyum.entity.Menu;
import org.koreait.yumyum.entity.MenuOption;
import org.koreait.yumyum.entity.MenuOptionDetail;
import org.koreait.yumyum.repository.MenuOptionDetailRepository;
import org.koreait.yumyum.repository.MenuOptionRepository;
import org.koreait.yumyum.service.MenuOptionDetailService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuOptionDetailServiceImpl implements MenuOptionDetailService {

    private final MenuOptionDetailRepository menuOptionDetailRepository;

    private final MenuOptionRepository menuOptionRepository;

    @Override
    public ResponseDto<MenuOptionDetailResponseDto> addOptionDetail(MenuOptionDetailRequestDto dto, Long id) {
        MenuOptionDetailResponseDto data = null;

        try {
            MenuOption optionId = menuOptionRepository.findById(dto.getMenuOptionId())
                    .orElseThrow(() -> new Error(ResponseMessage.NOT_EXIST_DATA));

            MenuOptionDetail menuOptionDetail = MenuOptionDetail.builder()
                    .menuOption(optionId)
                    .optionDetailName(dto.getOptionDetailName())
                    .additionalFee(dto.getAdditionalFee())
                    .build();

            MenuOptionDetail saveOptionDetail = menuOptionDetailRepository.save(menuOptionDetail);
            data = new MenuOptionDetailResponseDto(saveOptionDetail);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }


    @Override
    public ResponseDto<MenuOptionDetailResponseDto> updateOptionDetail(MenuOptionDetailUpdateRequestDto dto, Long optionDetailId,Long pkId, Long id) {
        MenuOptionDetailResponseDto data = null;

        List<MenuOptionDetail> menuOptionDetails = menuOptionDetailRepository.findByMenuOptionIdAndId(optionDetailId, pkId);
        try {
            for (int i = 0; i < dto.getDetailName().size(); i++){
                menuOptionDetails.get(i).setOptionDetailName(dto.getDetailName().get(i).getOptionDetailName());
                menuOptionDetails.get(i).setAdditionalFee(dto.getDetailName().get(i).getAdditionalFee());
                MenuOptionDetail saveOptionDetail = menuOptionDetailRepository.save(menuOptionDetails.get(i));
                data = new MenuOptionDetailResponseDto(saveOptionDetail);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteOptionDetail(Long optionDetailId, Long id) {

        try {
            Optional<MenuOptionDetail> menuOptionDetailOptional = menuOptionDetailRepository.findById(optionDetailId);

            if (menuOptionDetailOptional.isPresent()) {
                MenuOptionDetail menuOptionDetail = menuOptionDetailOptional.get();
                menuOptionDetailRepository.delete(menuOptionDetail);
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

