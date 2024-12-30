package org.koreait.yumyum.controller;


import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ApiMappingPattern;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.stat.response.DailySalesResponseDto;
import org.koreait.yumyum.dto.stat.response.MonthSalesResponseDto;
import org.koreait.yumyum.dto.stat.response.YearSalesResponseDto;
import org.koreait.yumyum.service.StatsPeriodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.STATS)
@RequiredArgsConstructor
public class statsPeriodController {
    private final StatsPeriodService statsPeriodService;

    public static final String GET_STATS_PERIOD_DAILY = "/daily/{orderDate}";
    public static final String GET_STATS_PERIOD_MONTH = "/month/{orderDate}";
    public static final String GET_STATS_PERIOD_YEAR = "/year/{orderDate}";

    @GetMapping(GET_STATS_PERIOD_DAILY)
    public ResponseEntity<ResponseDto<List<DailySalesResponseDto>>> findDailySales(
            @PathVariable String orderDate,
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<List<DailySalesResponseDto>> response = statsPeriodService.findDailySales(orderDate, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(GET_STATS_PERIOD_MONTH)
    public ResponseEntity<ResponseDto<List<MonthSalesResponseDto>>> findMonthSales(
            @PathVariable String orderDate,
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<List<MonthSalesResponseDto>> response = statsPeriodService.findMonthSales(orderDate, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(GET_STATS_PERIOD_YEAR)
    public ResponseEntity<ResponseDto<List<YearSalesResponseDto>>> findYearSales(
            @PathVariable String orderDate,
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<List<YearSalesResponseDto>> response = statsPeriodService.findYearSales(orderDate, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}