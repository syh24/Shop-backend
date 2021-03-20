package com.shop.mew.web.dto;

import com.shop.mew.domain.cart.Cart;
import com.shop.mew.domain.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class OrderItemResponseDto {

    private Long id;
    private String deliveryMessage;
    private String address;
    private Integer amount;
    private OrderStatus orderStatus;
    private String orderName;
    private String orderNumber;
    private String createdDate;
    private List<Cart> carts;
}
