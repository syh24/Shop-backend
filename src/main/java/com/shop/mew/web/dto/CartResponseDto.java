package com.shop.mew.web.dto;

import com.shop.mew.domain.item.Item;
import com.shop.mew.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@Builder
@ToString
public class CartResponseDto {
    private Long id;
    private UserResponseDto user;
    private ItemResponseDto item;
    private Integer itemCount;
}
