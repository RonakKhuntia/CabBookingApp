package com.clone.backend.uber.strategy;

import com.clone.backend.uber.entity.Driver;
import com.clone.backend.uber.entity.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDrivers(RideRequest rideRequest);
}
