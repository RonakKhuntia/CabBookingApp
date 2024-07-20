package com.clone.uber.service.impl;

import com.clone.uber.dto.DriverDto;
import com.clone.uber.dto.SignUpDto;
import com.clone.uber.dto.UserDto;
import com.clone.uber.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public String login(String email, String password) {
        return null;
    }

    @Override
    public UserDto signup(SignUpDto signUpDto) {
        return null;
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
