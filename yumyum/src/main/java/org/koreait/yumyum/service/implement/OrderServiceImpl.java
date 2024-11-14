package org.koreait.yumyum.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.order.response.OrderListResponseDto;
import org.koreait.yumyum.dto.order.response.OrderResponseDto;
import org.koreait.yumyum.dto.order.response.UpdateOrderStateDto;
import org.koreait.yumyum.entity.Order;
import org.koreait.yumyum.repository.OrderRepository;
import org.koreait.yumyum.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public ResponseDto<List<OrderListResponseDto>> getAllOrders(String orderState) {
        List<OrderListResponseDto> data = null;

        try {
            List<Order> orderList = orderRepository.findByOrderState(orderState);

            data = orderList.stream()
                    .map(OrderListResponseDto::new)
                    .collect(Collectors.toList());

        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<OrderResponseDto> getOrder(Long orderId) {
        OrderResponseDto data = null;

        try {
            Optional<Order> optionalOrder = orderRepository.findById(orderId);

            if(optionalOrder.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_ORDER);
            }

            Order order = optionalOrder.get();
            data = new OrderResponseDto(order);

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> updateOrderState(UpdateOrderStateDto dto) {
        Long orderId = dto.getOrderId();
        String orderState = dto.getOrderState();
        OrderResponseDto data = null;

        try {
            Optional<Order> optionalOrder = orderRepository.findById(orderId);

            if (optionalOrder.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_ORDER);
            }

            Order order = optionalOrder.get();
            order.setOrderState(orderState);

            orderRepository.save(order);
            data = new OrderResponseDto(order);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}

