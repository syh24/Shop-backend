package com.shop.mew.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserResponseDto {

    @Getter
    @Builder
    public static class Profile {
        private String name;
        private String email;
        private LocalDate birth;
        private String agreeMessageByEmail;
        private String address;
    }
}
