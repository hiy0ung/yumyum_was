package org.koreait.yumyum.dto.order.response;

import lombok.Getter;
import org.koreait.yumyum.entity.OrderDetail;

@Getter
public class OrderDetailResponseDto {
    private String orderProductName;
    private int quantity;

    public  OrderDetailResponseDto(OrderDetail orderDetail) {
        this.orderProductName = orderDetail.getOrderProductName();
        this.quantity = orderDetail.getQuantity();
    }
}
