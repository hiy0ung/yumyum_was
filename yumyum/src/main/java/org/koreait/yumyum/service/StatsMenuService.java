package org.koreait.yumyum.service;

import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.stat.response.StatsMenuResponseDto;

import java.util.List;



public interface StatsMenuService {
    ResponseDto<List<StatsMenuResponseDto>> getTodaySalesByOrderDate();

    ResponseDto<List<StatsMenuResponseDto>> getDaySalesByOrderDate(String orderDate);

    ResponseDto<List<StatsMenuResponseDto>> getMonthSalesByOrderDate(String orderDate);
}