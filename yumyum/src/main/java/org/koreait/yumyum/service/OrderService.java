package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.order.response.OrderResponseDto;

import java.util.List;

public interface OrderService {
    ResponseDto<List<OrderResponseDto>> getAllOrders(Long id);
    ResponseDto<OrderResponseDto> updateOrderState(Long id, Long orderId, String updateOrderState);
}
