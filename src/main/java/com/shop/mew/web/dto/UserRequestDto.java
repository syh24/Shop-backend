package com.shop.mew.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRequestDto {


    @ApiModelProperty (value = "이름", required = true)
    private String name;

    @ApiModelProperty (value = "로그인 이메일", required = true)
    private String email;

    @ApiModelProperty (value = "로그인 비밀번호", required = true)
    private String password;

    @ApiModelProperty (value = "주소", required = true)
    private String address;

    @Builder
    public UserRequestDto (String name,String email,String password,String address){
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }
}
