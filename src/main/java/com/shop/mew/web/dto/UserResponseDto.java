package com.shop.mew.web.dto;

import lombok.Data;


@Data
public class UserResponseDto {

    private String name;
    private String email;
    private String address;

    public UserResponseDto(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
