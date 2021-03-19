package com.shop.mew.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.util.UUID;

@Getter
@Setter
@ToString
public class CartRequestDto {

    private Long userId;
    private Long itemId;
    @Min(value = 1, message = "수량은 1개 이상이여야 합니다.")
    private Integer count;
}
