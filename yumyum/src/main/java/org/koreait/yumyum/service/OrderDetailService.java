package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.order.response.OrderDetailResponseDto;

import java.util.List;

public interface OrderDetailService {
    ResponseDto<List<OrderDetailResponseDto>> getOrderDetail(Long id);
}
