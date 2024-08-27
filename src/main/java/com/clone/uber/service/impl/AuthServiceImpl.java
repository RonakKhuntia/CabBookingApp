package com.clone.uber.service.impl;

import com.clone.uber.dto.DriverDto;
import com.clone.uber.dto.SignUpDto;
import com.clone.uber.dto.UserDto;
import com.clone.uber.entity.Driver;
import com.clone.uber.entity.User;
import com.clone.uber.entity.enums.Role;
import com.clone.uber.exception.ResourceNotFoundException;
import com.clone.uber.exception.RuntimeConflictException;
import com.clone.uber.repository.UserRepository;
import com.clone.uber.service.AuthService;
import com.clone.uber.service.DriverService;
import com.clone.uber.service.RiderService;
import com.clone.uber.service.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Set;

import static com.clone.uber.entity.enums.Role.DRIVER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final DriverService driverService;
    private final WalletService walletService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    //sign up service impl
    @Override
    @Transactional
    public UserDto signup(SignUpDto signUpDto) {
        User user = userRepository.findByEmail(signUpDto.getEmail()).orElse(null);
        if(user != null)
            throw new RuntimeConflictException("Cannot signup, User already exists with email "+signUpDto.getEmail());

        User mappedUser = modelMapper.map(signUpDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

//        create user related entities
        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId, String vehicleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(STR."User not found with id \{userId}"));

        if(user.getRoles().contains(DRIVER))
            throw new RuntimeConflictException(STR."User with id \{userId} is already a Driver");

        Driver createDriver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();
        user.getRoles().add(DRIVER);
        userRepository.save(user);
        Driver savedDriver = driverService.createNewDriver(createDriver);
        return modelMapper.map(savedDriver, DriverDto.class);
    }

}
