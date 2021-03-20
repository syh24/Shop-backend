package com.shop.mew.service;

import com.shop.mew.domain.orderitem.OrderItem;
import com.shop.mew.domain.orderitem.OrderItemRepository;
import com.shop.mew.domain.user.UserRepository;
import com.shop.mew.exception.NotExistOrderException;
import com.shop.mew.web.dto.OrderItemResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderItemService {

    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderItemResponseDto getOrderDetails(Long orderId) {
        OrderItem orderItem = orderItemRepository.findById(orderId)
                .orElseThrow(() -> new NotExistOrderException("존재하지 않는 주문입니다."));
        return orderItem.toResponseDto();
    }

    public HashMap<String, Object> getAllOrder(Long userId) {
        List<OrderItem> orderItem = orderItemRepository.findAllByUserId(userId);
        List<OrderItemResponseDto> orderItems = new ArrayList<>();
        for (OrderItem item : orderItem) {
            orderItems.add(item.toResponseDto());
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("orderItemList", orderItems);
        return resultMap;
    }

}
