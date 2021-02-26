package com.shop.mew.web.dto;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class UserRequestDto {

    @Getter
    public static class Join {

        @NotBlank
        @Length(max = 50)
        private String name;

        @Length(max = 50)
        private String email;

        @NotBlank
        @Length(max = 50)
        private String password;

        //@NotBlank
        private LocalDate birth;

        @NotBlank
        private String address;

        private boolean agreeMessageByEmail;

        public Join(@NotBlank @Length(max = 50) String name,
                    @Length(max = 50) String email,
                    @NotBlank @Length(max = 50) String password,
                    LocalDate birth, @NotBlank String address,
                    boolean agreeMessageByEmail) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.birth = birth;
            this.address = address;
            this.agreeMessageByEmail = agreeMessageByEmail;
        }
    }
}
