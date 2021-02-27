package com.shop.mew.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    DELIVERY("배송중"), COMPLETE("배송완료");

    private final String status;

}
