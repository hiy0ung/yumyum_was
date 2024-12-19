package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.order.response.OrderResponseDto;

import java.util.List;

public interface OrderService {
    ResponseDto<List<OrderResponseDto>> getAllOrders();

    ResponseDto<OrderResponseDto> updateOrderState(Long id, String updateOrderState);
}
