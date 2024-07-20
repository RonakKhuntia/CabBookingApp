package com.clone.uber.strategy;

import com.clone.uber.dto.RideRequestDto;
import com.clone.uber.entity.Driver;

import java.util.List;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDriver(RideRequestDto rideRequestDto);
}
