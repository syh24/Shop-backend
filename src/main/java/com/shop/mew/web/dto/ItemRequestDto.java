package com.shop.mew.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
public class ItemRequestDto {

    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class Register {

        @ApiModelProperty(value = "상품 이름", required = true)
        private String name;

        @ApiModelProperty (value = "카테고리", required = true)
        private String category;

        @ApiModelProperty (value = "가격", required = true)
        private Integer price;

        @ApiModelProperty (value = "수량", required = true)
        private Integer count;

        @ApiModelProperty (value = "이미지")
        private String img;

        @Builder
        public Register(String name,
                        String category,
                        Integer price,
                        Integer count,
                        String img) {
            checkNotNull(name, "name must be provided.");
            checkNotNull(category, "category must be provided.");
            checkNotNull(price, "price must be provided.");
            checkNotNull(count, "count must be provided.");
            this.name = name;
            this.category = category;
            this.price = price;
            this.count = count;
            this.img = img;
        }
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class Update {
        private String name;

        private String category;

        private Integer price;

        private Integer count;

        private String img;

        @Builder
        public Update(String name,
                        String category,
                        Integer price,
                        Integer count,
                        String img) {
            this.name = name;
            this.category = category;
            this.price = price;
            this.count = count;
            this.img = img;
        }
    }
}
