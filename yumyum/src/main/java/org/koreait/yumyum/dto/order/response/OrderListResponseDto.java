package org.koreait.yumyum.dto.order.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.yumyum.entity.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderListResponseDto {
    private Long orderId;
    private List<OrderDetailResponseDto> orderDetailResponseDtos;
    private String deliveryAddress;
    private LocalDateTime orderDate;
    private int totalPrice;

    public OrderListResponseDto(Order order) {
        this.orderId = order.getId();
        this.orderDetailResponseDtos = order.getOrderDetail().stream()
                .map(OrderDetailResponseDto::new)
                .collect(Collectors.toList());
        this.deliveryAddress = order.getDeliveryAddress();
        this.orderDate = order.getOrderDate();
        this.totalPrice = order.getTotalPrice();
    }
}

