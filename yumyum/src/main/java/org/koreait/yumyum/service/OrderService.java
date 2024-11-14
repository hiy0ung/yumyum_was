package org.koreait.yumyum.service;

import jakarta.validation.Valid;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.order.response.OrderListResponseDto;
import org.koreait.yumyum.dto.order.response.OrderResponseDto;
import org.koreait.yumyum.dto.order.response.UpdateOrderStateDto;

import java.util.List;

public interface OrderService {
    ResponseDto<List<OrderListResponseDto>> getAllOrders(String orderState);

    ResponseDto<OrderResponseDto> getOrder(Long orderId);

    ResponseDto<Void> updateOrderState(UpdateOrderStateDto updateOrderState);
}
