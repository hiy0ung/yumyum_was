package org.koreait.yumyum.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ApiMappingPattern;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.stat.response.QuantityStatsTimeResponseDto;
import org.koreait.yumyum.dto.stat.response.RevenueStatsTimeResponseDto;
import org.koreait.yumyum.service.implement.StatsTimeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.STATS)
@RequiredArgsConstructor
public class StatsTimeController {

    private final StatsTimeServiceImpl statsTimeService;

    public static final String GET_REVENUE_STATS_TIME = "/time/revenue/{orderDate}";
    public static final String GET_QUANTITY_STATS_TIME = "/time/quantity/{orderDate}";

    @GetMapping(GET_REVENUE_STATS_TIME) // 2007-12-03T10:15:30
    public ResponseEntity<ResponseDto<List<RevenueStatsTimeResponseDto>>> getRevenueByHour(
            @PathVariable String orderDate,
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<List<RevenueStatsTimeResponseDto>> response = statsTimeService.getRevenueByHour(orderDate, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(GET_QUANTITY_STATS_TIME)
    public ResponseEntity<ResponseDto<List<QuantityStatsTimeResponseDto>>> getQuantityByHour(
            @PathVariable String orderDate,
            @AuthenticationPrincipal Long id
    ) {
            ResponseDto<List<QuantityStatsTimeResponseDto>> response = statsTimeService.getQuantityByHour(orderDate, id);
            HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return ResponseEntity.status(status).body(response);

    }
}