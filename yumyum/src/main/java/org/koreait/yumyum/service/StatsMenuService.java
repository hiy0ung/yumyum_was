package org.koreait.yumyum.service;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.stat.response.StatsMenuResponseDto;
import org.koreait.yumyum.repository.StatsMenuRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsMenuService {

    private final StatsMenuRepository statsMenuRepository;

    
    // 하루
    public ResponseDto<List<StatsMenuResponseDto>> getDailySales(String date) {
        List<StatsMenuResponseDto> data;

        try {
            LocalDate localDate = LocalDate.parse(date);
            List<Object[]> dailySales = statsMenuRepository.findDailySales(
                    localDate.getYear(),
                    localDate.getMonthValue(),
                    localDate.getDayOfMonth()
            );
            data = dailySales.stream()
                    .map(value -> new StatsMenuResponseDto(
                            (String) value[0],
                            (Long) value[1]
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
    // 주간
}