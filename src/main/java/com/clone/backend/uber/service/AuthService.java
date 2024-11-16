package com.clone.backend.uber.service;

import com.clone.backend.uber.dto.DriverDto;
import com.clone.backend.uber.dto.SignupDto;
import com.clone.backend.uber.dto.UserDto;

public interface AuthService {
    String login(String email, String password);
    UserDto signup(SignupDto signupDto);
    DriverDto onboardNewDriver(Long userId);
}
