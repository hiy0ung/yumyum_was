package org.koreait.yumyum.dto.order.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.yumyum.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderResponseDto {
    private List<OrderDetailResponseDto> orderDetailResponseDtos;
    private int orderTotalPrice;
    private String deliveryAddress;

    public OrderResponseDto(Order order) {
        this.orderDetailResponseDtos = order.getOrderDetail().stream()
                .map(OrderDetailResponseDto::new)
                .collect(Collectors.toList());
        this.orderTotalPrice = order.getTotalPrice();
        this.deliveryAddress = order.getDeliveryAddress();
    }
}

