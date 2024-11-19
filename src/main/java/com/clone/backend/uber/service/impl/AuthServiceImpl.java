package com.clone.backend.uber.service.impl;

import com.clone.backend.uber.dto.DriverDto;
import com.clone.backend.uber.dto.SignupDto;
import com.clone.backend.uber.dto.UserDto;
import com.clone.backend.uber.entity.Rider;
import com.clone.backend.uber.entity.User;
import com.clone.backend.uber.enums.Role;
import com.clone.backend.uber.exception.RuntimeConflictException;
import com.clone.backend.uber.repository.UserRepository;
import com.clone.backend.uber.service.AuthService;
import com.clone.backend.uber.service.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDto signup(SignupDto signupDto) {
        userRepository.findByEmail(signupDto.getEmail())
                .orElseThrow(() -> new RuntimeConflictException(String.format("User with email %s already exists",
                        signupDto.getEmail())));
        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);
        riderService.createNewRider(savedUser);
        // TODO Add Wallet-Service
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
