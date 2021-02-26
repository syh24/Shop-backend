package com.shop.mew.web;

import com.shop.mew.service.UserService;
import com.shop.mew.web.dto.UserRequestDto;
import com.shop.mew.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    //회원 생성
    @PostMapping ("/user")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserRequestDto.Join userRequestDto) {
        userService.add(userRequestDto);
        return ResponseEntity.ok("회원 가입 성공");
    }

    //비밀번호 찾기
    @PostMapping("/password")
    public String findPassword(@Valid @RequestBody UserRequestDto.Find userRequestDto) {
        return userService.findPassword(userRequestDto);
    }

    //회원 프로필 조회
    @GetMapping("/user/{id}")
    public UserResponseDto.Profile getProfile(@PathVariable("id") Long id){
        return userService.getProfile(id);
    }
}
