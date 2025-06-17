package org.koreait.yumyum.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.stat.response.QuantityStatsTimeResponseDto;
import org.koreait.yumyum.dto.stat.response.RevenueStatsTimeResponseDto;
import org.koreait.yumyum.repository.StatsTimeRepository;
import org.koreait.yumyum.service.StatsTimeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsTimeServiceImpl implements StatsTimeService {
    private final StatsTimeRepository statsTimeRepository;

    @Override
    public ResponseDto<List<RevenueStatsTimeResponseDto>> getRevenueByHour(String orderDate, Long id) {
        List<RevenueStatsTimeResponseDto> data = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime localDateTime = LocalDateTime.parse(orderDate, formatter);

            int year = localDateTime.getYear();
            int month = localDateTime.getMonthValue();
            int day = localDateTime.getDayOfMonth();

            List<Object[]> convertDto = statsTimeRepository.findRevenueByHour(year, month, day, id);

            data = convertDto.stream()
                    .map(dto -> new RevenueStatsTimeResponseDto(
                            ((java.sql.Date) dto[0]).toLocalDate(),
                            (Integer) dto[1],
                            ((BigDecimal) dto[2]).longValue()
                    ))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("날짜 변환 오류: " + orderDate);
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<QuantityStatsTimeResponseDto>> getQuantityByHour(String orderDate, Long id) {
        List<QuantityStatsTimeResponseDto> data = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime localDateTime = LocalDateTime.parse(orderDate, formatter);

            int year = localDateTime.getYear();
            int month = localDateTime.getMonthValue();
            int day = localDateTime.getDayOfMonth();

            List<Object[]> convertDto = statsTimeRepository.findOrderQuantityByHour(year, month, day, id);

            data = convertDto.stream()
                    .map(dto -> new QuantityStatsTimeResponseDto(
                            ((java.sql.Date)dto[0]).toLocalDate(),
                            (Integer) dto[1],
                            (Long) dto[2]
                    )).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("날짜 변환 오류: " + orderDate);
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        if (data == null || data.isEmpty()) {
            System.out.println("데이터가 없습니다: " + orderDate);
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}