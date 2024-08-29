package com.clone.uber.service;

import com.clone.uber.dto.DriverDto;
import com.clone.uber.dto.SignUpDto;
import com.clone.uber.dto.UserDto;

public interface AuthService {

    String[] login(String email, String password);

    UserDto signup(SignUpDto signupDto);

    DriverDto onboardNewDriver(Long userId, String vehicleId);

    String refreshToken(String refreshToken);
}
