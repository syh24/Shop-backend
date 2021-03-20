package com.shop.mew.web;

import com.shop.mew.service.OrderItemService;
import com.shop.mew.web.dto.OrderItemResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "상품 주문 APIs")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class OrderItemController {

    private final OrderItemService orderItemService;

    @ApiOperation(value = "주문 상세")
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderItemResponseDto> orderItem(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderItemService.getOrderDetails(orderId));
    }

    @ApiOperation(value = "전체 주문 조회")
    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<?> getAllOrder(@PathVariable Long userId) {
        return ResponseEntity.ok(orderItemService.getAllOrder(userId));
    }

}
