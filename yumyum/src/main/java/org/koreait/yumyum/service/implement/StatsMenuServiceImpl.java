package org.koreait.yumyum.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.stat.response.StatsMenuResponseDto;
import org.koreait.yumyum.repository.StatsMenuRepository;
import org.koreait.yumyum.service.StatsMenuService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsMenuServiceImpl implements StatsMenuService {

    private final StatsMenuRepository statsMenuRepository;

    @Override
    public ResponseDto<List<StatsMenuResponseDto>> getTodaySalesByOrderDate() {
        List<StatsMenuResponseDto> data = null;

        List<Object[]> convertDto;
        try {
            convertDto = statsMenuRepository.findTodayTotalPriceAndQuantityByOrderDate();
            data = convertDto.stream()
                    .map(dto -> new StatsMenuResponseDto(
                            ((java.sql.Date) dto[0]).toLocalDate(),
                            (String) dto[1],
                            ((Long) dto[2]).intValue(),
                            ((Double) dto[3]).longValue()
                    )).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<StatsMenuResponseDto>> getDaySalesByOrderDate(String orderDate) {
        List<StatsMenuResponseDto> data = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate localDate = LocalDate.parse(orderDate, formatter);

            int year = localDate.getYear();
            int month = localDate.getMonthValue();
            int day = localDate.getDayOfMonth();

            List<Object[]> convertDto = statsMenuRepository.findDayTotalPriceAndQuantityByOrderDate(year, month, day);
            data = convertDto.stream()
                    .map(dto -> new StatsMenuResponseDto(
                            ((java.sql.Date) dto[0]).toLocalDate(),
                            (String) dto[1],
                            ((Long) dto[2]).intValue(),
                            ((Double) dto[3]).longValue()
                    )).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<StatsMenuResponseDto>> getMonthSalesByOrderDate(String orderDate) {
        List<StatsMenuResponseDto> data = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate localDate = LocalDate.parse(orderDate, formatter);

            int year = localDate.getYear();
            int month = localDate.getMonthValue();

            List<Object[]> convertDto = statsMenuRepository.findMonthTotalPriceAndQuantityByOrderDate(year, month);
            data = convertDto.stream()
                    .map(dto -> new StatsMenuResponseDto(
                            ((java.sql.Date) dto[0]).toLocalDate(),
                            (String) dto[1],
                            ((Long) dto[2]).intValue(),
                            ((Double) dto[3]).longValue()
                    )).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
