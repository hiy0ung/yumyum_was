package org.koreait.yumyum.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ApiMappingPattern;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.order.response.OrderDetailResponseDto;
import org.koreait.yumyum.service.OrderDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.ORDER)
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    private static final String GET_ORDER_DETAIL_BY_ID = "/detail/{id}";

    @GetMapping(GET_ORDER_DETAIL_BY_ID)
    public ResponseEntity<ResponseDto<List<OrderDetailResponseDto>>> getOrderDetail(@PathVariable Long id) {
        ResponseDto<List<OrderDetailResponseDto>> response = orderDetailService.getOrderDetail(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
