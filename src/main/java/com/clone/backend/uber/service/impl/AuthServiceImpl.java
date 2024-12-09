package com.clone.backend.uber.service.impl;

import com.clone.backend.uber.model.DriverDto;
import com.clone.backend.uber.model.SignupDto;
import com.clone.backend.uber.model.UserDto;
import com.clone.backend.uber.entity.User;
import com.clone.backend.uber.enums.Role;
import com.clone.backend.uber.exception.RuntimeConflictException;
import com.clone.backend.uber.repository.UserRepository;
import com.clone.backend.uber.service.AuthService;
import com.clone.backend.uber.service.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if (user != null) {
            throw new RuntimeConflictException(String.format("User with email %s already exists",
                    signupDto.getEmail()));
        }
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
