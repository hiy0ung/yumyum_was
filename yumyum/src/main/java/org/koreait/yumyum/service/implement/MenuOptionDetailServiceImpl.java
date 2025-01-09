package org.koreait.yumyum.service.implement;

import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.generic.ClassGen;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.menu.request.MenuOptionDetailRequestDto;
import org.koreait.yumyum.dto.menu.response.MenuOptionDetailResponseDto;
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
    public ResponseDto<MenuOptionDetailResponseDto> updateOptionDetail(MenuOptionDetailRequestDto dto, Long optionDetailId, Long id) {
        MenuOptionDetailResponseDto data = null;

        try {
            List<MenuOptionDetail> menuOptionDetailOptional = menuOptionDetailRepository.findIdByMenuOptionId(optionDetailId);

//            System.out.println("변경할 메뉴 디테일 ID: " + menuOptionDetail.getId());
//            List<Long> optionDetailIdList = new ArrayList<>();
//            for (MenuOptionDetail menuOptionDetail : menuOptionDetailOptional) {
//                optionDetailIdList.add(menuOptionDetail.getId());
//            }
//            System.out.println(optionDetailIdList);


                System.out.println("옵션 디테일 이름: " + dto.getOptionDetailName());
                System.out.println("옵션 디테일 추가금: " + dto.getAdditionalFee());
                System.out.println("옵션 아이디: " + optionDetailId);
                for(MenuOptionDetail menuOptionDetail : menuOptionDetailOptional) {
                    System.out.println("옵션 디테일 아이디: " + menuOptionDetail.getId());

                }

//                menuOptionDetailRepository.update(dto.getOptionDetailName(), dto.getAdditionalFee(), optionDetail.getId(), optionDetailId);






//
//            for(int i=0; i < menuOptionDetailOptional.size(); i++) {
//                MenuOptionDetail menuOptionDetail = menuOptionDetailOptional.get(i);
//                System.out.println("메뉴 옵션 디테일 찾아온거의 이름" + menuOptionDetail.getOptionDetailName());
//                System.out.println(optionDetailNames.get(i));
//                menuOptionDetail.setOptionDetailName(optionDetailNames.get(i));
//                System.out.print("저장할 옵션 디테일 가격");
//                System.out.println("여기 1");
//                System.out.println(additionalFees.get(i));
//                menuOptionDetail.setAdditionalFee(additionalFees.get(i));
//                System.out.println("여기 2");
//                MenuOptionDetail saveOptionDetail = menuOptionDetailRepository.save(menuOptionDetail);
//                data = new MenuOptionDetailResponseDto(saveOptionDetail);
//            }

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

