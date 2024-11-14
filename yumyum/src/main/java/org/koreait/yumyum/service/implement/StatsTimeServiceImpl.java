package org.koreait.yumyum.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.stat.response.StatsTimeResponseDto;
import org.koreait.yumyum.repository.OrderRepository;
import org.koreait.yumyum.service.StatsTimeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsTimeServiceImpl implements StatsTimeService {
    private final OrderRepository orderRepository;

    @Override
    public ResponseDto<List<StatsTimeResponseDto>> getRevenueByOrderDate(String orderDate) {
        List<StatsTimeResponseDto> data = null;
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime localDateTime = LocalDateTime.parse(orderDate, formatter);

            int year = localDateTime.getYear();
            int month = localDateTime.getMonthValue();
            int day = localDateTime.getDayOfMonth();

            List<Object[]> convertDto = orderRepository.findRevenueByOrderDate(year, month, day);

            data = convertDto.stream()
                    .map(dto -> new StatsTimeResponseDto(
                            ((java.sql.Date) dto[0]).toLocalDate(),
//                          (LocalDate) dto[0],
                            (Integer) dto[1],
                            (Long) dto[2]
                    ))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
