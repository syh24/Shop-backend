package com.shop.mew.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class ReviewResponseDto {

    private String content;
    private int rate;
}
