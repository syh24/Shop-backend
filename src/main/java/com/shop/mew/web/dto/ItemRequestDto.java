package com.shop.mew.web.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
public class ItemRequestDto {

    @NoArgsConstructor
    @Data
    public static class Register {
        @NotBlank
        private String name;

        @NotBlank
        private String category;

        @NotBlank
        private Integer price;

        @NotBlank
        private Integer count;

        private String img;

        @Builder
        public Register(String name,
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
