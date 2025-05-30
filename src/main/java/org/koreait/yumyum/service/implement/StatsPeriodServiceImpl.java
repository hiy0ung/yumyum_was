package org.koreait.yumyum.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.stat.response.DailySalesResponseDto;
import org.koreait.yumyum.dto.stat.response.MonthSalesResponseDto;
import org.koreait.yumyum.dto.stat.response.YearSalesResponseDto;
import org.koreait.yumyum.repository.StatsPeriodRepository;
import org.koreait.yumyum.service.StatsPeriodService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsPeriodServiceImpl implements StatsPeriodService {
    private final StatsPeriodRepository periodRepository;

    @Override
    public ResponseDto<List<DailySalesResponseDto>> findDailySales(String orderDate, Long id) {
        List<DailySalesResponseDto> data = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDate localDateTime = LocalDate.parse(orderDate, formatter);

            int year = localDateTime.getYear();
            int month = localDateTime.getMonthValue();

            List<Object[]> convertDto = periodRepository.findDailySales(year, month, id);

            data = convertDto.stream()
                    .map(dto -> new DailySalesResponseDto(
                                    ((java.sql.Date) dto[0]).toLocalDate(),
                                    ((BigDecimal) dto[1]).intValue()
                            )
                    ).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<MonthSalesResponseDto>> findMonthSales(String orderDate, Long id) {
        List<MonthSalesResponseDto> data = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDate localDateTime = LocalDate.parse(orderDate, formatter);

            int year = localDateTime.getYear();
            int month = localDateTime.getMonthValue();
            List<Object[]> convertDto = periodRepository.findMonthSales(year, month, id);

            data = convertDto.stream()
                    .map(dto -> new MonthSalesResponseDto(
                            ((Long) dto[0]).intValue(),
                            ((Long) dto[1]).intValue(),
                            ((BigDecimal) dto[2]).intValue()
                    )).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<YearSalesResponseDto>> findYearSales(String orderDate, Long id) {
        List<YearSalesResponseDto> data = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDate localDateTime = LocalDate.parse(orderDate, formatter);

            int year = localDateTime.getYear();
            List<Object[]> convertDto = periodRepository.findYearSales(year, id);

            data = convertDto.stream()
                    .map(dto -> new YearSalesResponseDto(
                            ((Long) dto[0]). intValue(),
                            ((BigDecimal) dto[1]).intValue()
                    )).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}