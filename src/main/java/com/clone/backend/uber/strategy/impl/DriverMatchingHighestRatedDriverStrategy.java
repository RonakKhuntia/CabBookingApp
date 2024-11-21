package com.clone.backend.uber.strategy.impl;

import com.clone.backend.uber.entity.Driver;
import com.clone.backend.uber.entity.RideRequest;
import com.clone.backend.uber.repository.DriverRepository;
import com.clone.backend.uber.strategy.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
    }
}
