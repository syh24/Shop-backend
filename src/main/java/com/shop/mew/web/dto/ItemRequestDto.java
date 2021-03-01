package com.shop.mew.web.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ItemRequestDto {

    @NoArgsConstructor
    @Data
    public static class Register {

        @NotBlank
        private String name;

        @NotBlank
        private String category;

        @NotNull
        private Integer price;

        @NotNull
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

    @Data
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
