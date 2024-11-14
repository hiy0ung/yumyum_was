package org.koreait.yumyum.controller;


import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ApiMappingPattern;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.order.response.OrderListResponseDto;
import org.koreait.yumyum.dto.order.response.OrderResponseDto;
import org.koreait.yumyum.dto.order.response.UpdateOrderStateDto;
import org.koreait.yumyum.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.ORDER)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private static final String GET_ORDER_LIST = "/list";
    private static final String UPDATE_ORDER_STATE = "/update/state";

    @GetMapping(GET_ORDER_LIST)
    public ResponseEntity<ResponseDto<List<OrderListResponseDto>>> getOrderList(@RequestParam String orderState) {
        ResponseDto<List<OrderListResponseDto>> response = orderService.getAllOrders(orderState);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseDto<OrderResponseDto>> getOrder(@PathVariable Long orderId) {
        ResponseDto<OrderResponseDto> response = orderService.getOrder(orderId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(UPDATE_ORDER_STATE)
    public ResponseEntity<ResponseDto<Void>> updateOrderState(@RequestBody UpdateOrderStateDto dto) {
        ResponseDto<Void> response = orderService.updateOrderState(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(null);
    }
}

