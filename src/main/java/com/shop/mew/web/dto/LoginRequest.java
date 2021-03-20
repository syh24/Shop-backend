package com.shop.mew.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@ToString
public class LoginRequest {

    @ApiModelProperty(value = "로그인 이메일", required = true)
    private String email;

    @ApiModelProperty(value = "로그인 비밀번호", required = true)
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
