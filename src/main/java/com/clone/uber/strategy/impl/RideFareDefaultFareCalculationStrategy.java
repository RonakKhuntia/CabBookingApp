package com.clone.uber.strategy.impl;

import com.clone.uber.dto.RideRequestDto;
import com.clone.uber.entity.RideRequest;
import com.clone.uber.service.DistanceService;
import com.clone.uber.strategy.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    //default ride fare strategy impl
    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_MULTIPLIER;
    }
}
