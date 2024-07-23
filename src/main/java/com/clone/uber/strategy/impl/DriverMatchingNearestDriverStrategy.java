package com.clone.uber.strategy.impl;

import com.clone.uber.dto.RideRequestDto;
import com.clone.uber.entity.Driver;
import com.clone.uber.strategy.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        return null;
    }
}