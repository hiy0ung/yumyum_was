package org.koreait.yumyum.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.order.response.OrderDetailResponseDto;
import org.koreait.yumyum.repository.OrderDetailRepository;
import org.koreait.yumyum.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public ResponseDto<List<OrderDetailResponseDto>> getOrderDetail(Long orderId) {
        List<OrderDetailResponseDto> data = null;

        try {
            data = orderDetailRepository.findOrderDetailsWithOptions(orderId).stream()
                    .map(dto -> new OrderDetailResponseDto(
                            (Long) dto[0],
                            (Long) dto[1],
                            (String) dto[2],
                            ((Timestamp) dto[3]).toLocalDateTime(),
                            (String) dto[4],
                            (String) dto[5],
                            dto[6] != null ? ((Number) dto[6]).intValue() : 0,
                            ((Number) dto[7]).intValue(),
                            (String) dto[8],
                            (String) dto[9],
                            dto[10] != null ? ((Number) dto[10]).intValue() : 0
                    )).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
