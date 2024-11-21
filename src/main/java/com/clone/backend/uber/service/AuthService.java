package com.clone.backend.uber.service;

import com.clone.backend.uber.model.DriverDto;
import com.clone.backend.uber.model.SignupDto;
import com.clone.backend.uber.model.UserDto;

public interface AuthService {
    String login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId);
}
