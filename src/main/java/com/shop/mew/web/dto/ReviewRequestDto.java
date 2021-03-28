package com.shop.mew.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Builder
@Getter
public class ReviewRequestDto {

    private Long userId;
    private Long itemId;
    private String content;
    private int rate;

    public ReviewRequestDto(Long userId, Long itemId, String content, int rate) {
        this.userId = userId;
        this.itemId = itemId;
        this.content = content;
        this.rate = rate;
    }
}
