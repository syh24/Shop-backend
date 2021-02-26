package com.shop.mew.domain.user;

import com.shop.mew.domain.BaseTimeEntity;
import com.shop.mew.web.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    private String password;

    private LocalDate birth;

    private String address;

    private boolean agreeMessageByEmail;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(Long id, String name, String email,
                String password, LocalDate birth,
                String address, boolean agreeMessageByEmail,
                Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.address = address;
        this.agreeMessageByEmail = agreeMessageByEmail;
        this.role = role;
    }

    public UserResponseDto.Profile toUserProfile(User user){
        return UserResponseDto.Profile.builder()
                .name(user.getName())
                .email(user.getEmail())
                .birth(user.getBirth())
                .agreeMessageByEmail(user.isAgreeMessageByEmail() ? "YES" : "NO")
                .address(user.getAddress())
                .build();
    }
}
