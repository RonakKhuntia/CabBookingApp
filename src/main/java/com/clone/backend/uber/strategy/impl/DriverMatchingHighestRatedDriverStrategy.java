package com.clone.backend.uber.strategy.impl;

import com.clone.backend.uber.dto.RideRequestDto;
import com.clone.backend.uber.entity.Driver;
import com.clone.backend.uber.strategy.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDrivers(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
