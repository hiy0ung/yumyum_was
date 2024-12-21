package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.stat.response.StatsTimeResponseDto;

import java.util.List;

public interface StatsTimeService {
    ResponseDto<List<StatsTimeResponseDto>> getRevenueByOrderDate(String orderDate);
}
