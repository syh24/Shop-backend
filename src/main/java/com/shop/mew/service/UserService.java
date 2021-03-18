package com.shop.mew.service;

import com.google.common.base.Preconditions;
import com.shop.mew.domain.user.Role;
import com.shop.mew.domain.user.User;
import com.shop.mew.domain.user.UserRepository;
import com.shop.mew.web.dto.UserRequestDto;
import com.shop.mew.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.google.common.base.Preconditions.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User add(UserRequestDto userRequestDto) {
        String password = userRequestDto.getPassword();
        checkNotNull(password,"password must be provided.");
        checkArgument(
                password.length() >= 8 && password.length() <= 15,
                "password length must be between 8 and 15 characters."
        );
        return userRepository.save(User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .address(userRequestDto.getAddress())
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .build());
    }

    @Transactional
    public Boolean login(String email, String password) {
        checkNotNull(password, "password must be provided.");
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
            user.login(passwordEncoder, password);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Boolean deleteUser(Long id) {
        try {
            userRepository.delete(userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다.")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserResponseDto getProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        return new UserResponseDto(user.getName(), user.getEmail(), user.getAddress());
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
