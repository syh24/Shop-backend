package com.shop.mew.web.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemResponseDto {

    private String itemName;
    private String category;
    private Integer price;
    private Integer count;
    private String img;

    public ItemResponseDto(String itemName, String category, Integer price, Integer count, String img) {
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.count = count;
        this.img = img;
    }
}
