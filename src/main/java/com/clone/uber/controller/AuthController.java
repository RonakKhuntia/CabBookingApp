package com.clone.uber.controller;

import com.clone.uber.dto.SignUpDto;
import com.clone.uber.dto.UserDto;
import com.clone.uber.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public UserDto signUp(@RequestBody SignUpDto signUpDto){
        return authService.signup(signUpDto);
    }

}
