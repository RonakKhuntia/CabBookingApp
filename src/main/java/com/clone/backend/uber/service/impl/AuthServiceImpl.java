package com.clone.backend.uber.service.impl;

import com.clone.backend.uber.dto.DriverDto;
import com.clone.backend.uber.dto.SignupDto;
import com.clone.backend.uber.dto.UserDto;
import com.clone.backend.uber.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDto signup(SignupDto signupDto) {
        return null;
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
