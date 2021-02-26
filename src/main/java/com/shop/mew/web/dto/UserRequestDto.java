package com.shop.mew.web.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class UserRequestDto {

    @Getter
    @NoArgsConstructor
    public static class Join {
        @NotBlank
        @Length(max = 50)
        private String name;

        @Email
        @Length(max = 50)
        private String email;

        @NotBlank
        @Length(max = 50)
        private String password;

        @Builder
        public Join(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }
    }
}
