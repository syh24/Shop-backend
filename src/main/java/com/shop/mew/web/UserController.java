package com.shop.mew.web;

import com.shop.mew.domain.user.User;
import com.shop.mew.service.UserService;
import com.shop.mew.web.dto.LoginRequest;
import com.shop.mew.web.dto.UserRequestDto;
import com.shop.mew.web.dto.UserResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Api(tags = "사용자 APIs")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원 등록")
    @PostMapping ("/join")
    public ResponseEntity<User> addUser(@RequestBody UserRequestDto userRequestDto) {
        User newUser = userService.add(userRequestDto);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @ApiOperation(value = "회원 삭제")
    @PostMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @ApiOperation (value = "로그인")
    @PostMapping ("/login")
    public ResponseEntity<?> loginUser (@RequestBody LoginRequest user){
        return ResponseEntity.ok(userService.login(user.getEmail(), user.getPassword()));
    }

    @ApiOperation(value = "이메일 중복확인")
    @PostMapping(path = "/exists")
    public ResponseEntity<Boolean> checkEmail(
            @RequestBody @ApiParam(value = "example: {\"email\": \"doongji.team@gmail.com\"}") Map<String, String> request
    ) {
        String email = request.get("email");
        return ResponseEntity.ok(
                userService.findByEmail(email).isPresent()
        );
    }

    //회원 프로필 조회
    @ApiOperation(value = "프로필 조회")
    @GetMapping("/{id}")
    public UserResponseDto getProfile(@PathVariable Long id){
        return userService.getProfile(id);
    }

    @ApiOperation(value = "모든 유저 조회")
    @GetMapping("/list")
    public List<UserResponseDto> findAllUser(){
        return userService.findAll()
                .stream().map(u -> new UserResponseDto(u.getName(), u.getEmail(), u.getAddress()))
                .collect(Collectors.toList());
    }
}
