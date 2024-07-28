package com.clone.uber.strategy.impl;

import com.clone.uber.dto.RideRequestDto;
import com.clone.uber.entity.Driver;
import com.clone.uber.entity.RideRequest;
import com.clone.uber.repository.DriverRepository;
import com.clone.uber.strategy.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    //highest rated driver strategy impl
    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
    }
}
