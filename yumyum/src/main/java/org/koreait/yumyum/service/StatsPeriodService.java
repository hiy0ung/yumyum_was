package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.stat.response.DailySalesResponseDto;
import org.koreait.yumyum.dto.stat.response.MonthSalesResponseDto;
import org.koreait.yumyum.dto.stat.response.YearSalesResponseDto;

import java.util.List;

public interface StatsPeriodService {
    ResponseDto<List<DailySalesResponseDto>> findDailySales(String orderDate);

    ResponseDto<List<MonthSalesResponseDto>> findMonthSales(String orderDate);

    ResponseDto<List<YearSalesResponseDto>> findYearSales(String orderDate);

}
