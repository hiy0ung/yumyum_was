package org.koreait.yumyum.dto.order.response;

import lombok.Getter;

@Getter
public class UpdateOrderStateDto {
    private Long orderId;
    private String orderState;
}
