package com.clone.uber.service.impl;

import com.clone.uber.dto.DriverDto;
import com.clone.uber.dto.SignUpDto;
import com.clone.uber.dto.UserDto;
import com.clone.uber.entity.User;
import com.clone.uber.entity.enums.Role;
import com.clone.uber.exception.RuntimeConflictException;
import com.clone.uber.repository.UserRepository;
import com.clone.uber.service.AuthService;
import com.clone.uber.service.RiderService;
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
        return null;
    }

    @Override
    public UserDto signup(SignUpDto signUpDto) {
        User user = userRepository.findByEmail(signUpDto.getEmail()).orElse(null);
        if(user == null){
            throw new RuntimeConflictException("User already exists with email : "+signUpDto.getEmail());
        }
        User user1 = modelMapper.map(signUpDto, User.class);
        user1.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(user1);
        riderService.createNewRider(savedUser);

        //TODO Add Wallet Service

        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }

}
