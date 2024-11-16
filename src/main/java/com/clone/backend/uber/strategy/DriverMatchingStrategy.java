package com.clone.backend.uber.strategy;

import com.clone.backend.uber.dto.RideRequestDto;
import com.clone.backend.uber.entity.Driver;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDrivers(RideRequestDto rideRequestDto);
}
