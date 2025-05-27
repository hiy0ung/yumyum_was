package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.stat.response.QuantityStatsTimeResponseDto;
import org.koreait.yumyum.dto.stat.response.RevenueStatsTimeResponseDto;

import java.util.List;

public interface StatsTimeService {
    ResponseDto<List<RevenueStatsTimeResponseDto>> getRevenueByHour(String orderDate, Long id);
    ResponseDto<List<QuantityStatsTimeResponseDto>> getQuantityByHour(String orderDate, Long id);
}
