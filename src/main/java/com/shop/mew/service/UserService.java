package com.shop.mew.service;

import com.shop.mew.domain.user.Role;
import com.shop.mew.domain.user.User;
import com.shop.mew.domain.user.UserRepository;
import com.shop.mew.web.dto.UserRequestDto;
import com.shop.mew.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void add(UserRequestDto.Join userRequestDto) {
        userRepository.save(User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .role(Role.USER)
                .build());
    }

    public UserResponseDto.Profile getProfile(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("회원이 존재하지 않습니다."));
        return user.toUserProfile(user);
    }
}
