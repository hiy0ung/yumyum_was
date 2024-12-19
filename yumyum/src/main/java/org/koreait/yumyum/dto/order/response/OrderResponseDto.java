package org.koreait.yumyum.dto.order.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long orderId; // 주문 번호로 사용
    private Long storeId;
    private String deliveryAddress;
    private LocalDateTime orderDate;
    private String guestNickname;
    private String orderState;
    private int sumTotalPrice;
}

