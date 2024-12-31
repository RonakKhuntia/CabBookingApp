package com.clone.backend.uber.controller;

import com.clone.backend.uber.model.DriverDto;
import com.clone.backend.uber.model.OnboardDriverDto;
import com.clone.backend.uber.model.SignupDto;
import com.clone.backend.uber.model.UserDto;
import com.clone.backend.uber.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto) {
        return new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);
    }

    @PostMapping("/onBoardNewDriver/{userId}")
    ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId,
                                               @RequestBody OnboardDriverDto onboardDriverDto) {
        return new ResponseEntity<>(authService.onboardNewDriver(userId, onboardDriverDto.getVehicleId()), HttpStatus.CREATED);
    }

}
