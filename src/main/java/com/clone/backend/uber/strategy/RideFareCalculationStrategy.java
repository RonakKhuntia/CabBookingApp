package com.clone.backend.uber.strategy;

import com.clone.backend.uber.dto.RideRequestDto;

public interface RideFareCalculationStrategy {
    double calculateFare(RideRequestDto rideRequestDto);
}
