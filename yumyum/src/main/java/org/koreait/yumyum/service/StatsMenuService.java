package org.koreait.yumyum.service;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.stat.response.StatsMenuResponseDto;
import org.koreait.yumyum.entity.Store;
import org.koreait.yumyum.repository.OrderRepository;
import org.koreait.yumyum.repository.StatsMenuRepository;
import org.koreait.yumyum.repository.StoreRepository;
import org.koreait.yumyum.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StatsMenuService {

    private final StatsMenuRepository statsMenuRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;


    // 하루
    public ResponseDto<List<StatsMenuResponseDto>> getDailySales(String userId, String date) {
        List<StatsMenuResponseDto> data;

        try {
            LocalDate localDate = LocalDate.parse(date);


            Store store = storeRepository.getStoreByUserId(userId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 사용자의 가게를 찾을 수 없습니다."));

            Long storeId = store.getId();
            System.out.println("가게 찾기 : " + storeId);


            List<Object[]> results = statsMenuRepository.findDailySalesByDateAndStore(localDate, storeId);

            data = results.stream()
                    .map(result -> new StatsMenuResponseDto(
                            (LocalDate) result[0],
                            (String) result[1],
                            ((Number) result[2]).longValue(),
                            ((Number) result[3]).longValue()
                    ))
                    .collect(Collectors.toList());
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return ResponseDto.setFailed("잘못된 날짜 형식입니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("데이터베이스 오류가 발생했습니다.");
        }

        return ResponseDto.setSuccess("성공", data);
    }
//    public ResponseDto<List<StatsMenuResponseDto>> getDailySales(String userId, String date) {
//        List<StatsMenuResponseDto> data;
//
//        try {
//            LocalDate localDate = LocalDate.parse(date);
//            List<Object[]> dailySales = statsMenuRepository.findMenuDailySales(
//                    localDate.getYear(),
//                    localDate.getMonthValue(),
//                    localDate.getDayOfMonth()
//            );
//            data = dailySales.stream()
//                    .map(value -> new StatsMenuResponseDto(
//                            (String) value[0],
//                            (Long) value[1]
//                    ))
//                    .collect(Collectors.toList());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
//        }
//        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
//    }

    // 주간
}