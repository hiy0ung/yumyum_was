package org.koreait.yumyum.dto.order.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponseDto {
    private Long orderId;
    private Long orderDetailId;
    private String deliveryAddress;
    private LocalDateTime orderDate;
    private String orderState;
    private String menuName;
    private Integer menuPrice;
    private Integer quantity;
    private String menuOptionName;
    private String menuOptionDetailName;
    private Integer additionalFee;
}
