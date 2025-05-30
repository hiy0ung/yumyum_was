package org.koreait.yumyum.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.yumyum.common.constant.ResponseMessage;
import org.koreait.yumyum.dto.ResponseDto;
import org.koreait.yumyum.dto.order.response.OrderResponseDto;
import org.koreait.yumyum.entity.Order;
import org.koreait.yumyum.entity.Store;
import org.koreait.yumyum.repository.OrderRepository;
import org.koreait.yumyum.repository.StoreRepository;
import org.koreait.yumyum.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;

    @Override
    public ResponseDto<List<OrderResponseDto>> getAllOrders(Long id) {

        Optional<Store> optionalStore = storeRepository.getStoreByUserId(id);
        if(optionalStore.isEmpty()) {
            ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
        }

        Long storeId = optionalStore.get().getId();
        List<OrderResponseDto> data = null;

        try {
            data = orderRepository.findAllOrderWithTotalPrice(storeId).stream()
                    .map(dto -> new OrderResponseDto(
                            (Long) dto[0],
                            (Long) dto[1],
                            (String) dto[2],
                            ((Timestamp) dto[3]).toLocalDateTime(),
                            (String) dto[4],
                            (String) dto[5],
                            ((BigDecimal) dto[6]).intValue()
                    )).collect(Collectors.toList());
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 주문 상태 수정
    @Override
    public ResponseDto<OrderResponseDto> updateOrderState(Long id, Long orderId, String updateOrderState) {
        OrderResponseDto data = null;

        Optional<Store> optionalStore = storeRepository.getStoreByUserId(id);
        if(optionalStore.isEmpty()) {
            ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
        }

        Long storeId = optionalStore.get().getId();

        try {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXIST_ORDER));

            order.setOrderState(updateOrderState);
            orderRepository.save(order);

            List<Object[]> orderList = orderRepository.findOrderWithTotalPriceById(orderId, storeId);

            if (orderList.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            Object[] dto = orderList.get(0);
            data = new OrderResponseDto(
                    (Long) dto[0],
                    (Long) dto[1],
                    (String) dto[2],
                    ((Timestamp) dto[3]).toLocalDateTime(),
                    (String) dto[4],
                    (String) dto[5],
                    ((BigDecimal) dto[6]).intValue()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}

