package org.koreait.yumyum.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ApiMappingPattern;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.stat.response.StatsMenuResponseDto;
import org.koreait.yumyum.service.StatsMenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.STATS) // /api/v1/stats;
public class StatsMenuController {

    private final StatsMenuService statsMenuService;

    public static final String GET_STATS_TODAY = "/menus/today";
    public static final String GET_STATS_DAY = "/menus/day/{orderDate}";
    public static final String GET_STATS_MONTH = "/menus/month/{orderDate}";

    @GetMapping(GET_STATS_TODAY)
    public ResponseEntity<ResponseDto<List<StatsMenuResponseDto>>> getTodaySalesByOrderDate() {
        ResponseDto<List<StatsMenuResponseDto>> response = statsMenuService.getTodaySalesByOrderDate();
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(GET_STATS_DAY) // 2024-12-20T10:00:00
    public ResponseEntity<ResponseDto<List<StatsMenuResponseDto>>> getDaySalesByOrderDate(@PathVariable String orderDate) {
        ResponseDto<List<StatsMenuResponseDto>> response = statsMenuService.getDaySalesByOrderDate(orderDate);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(GET_STATS_MONTH) // 2024-12-20T10:00:00
    public ResponseEntity<ResponseDto<List<StatsMenuResponseDto>>> getMonthSalesByOrderDate(@PathVariable String orderDate) {
        ResponseDto<List<StatsMenuResponseDto>> response = statsMenuService.getMonthSalesByOrderDate(orderDate);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}