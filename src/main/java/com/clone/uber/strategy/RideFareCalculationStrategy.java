package com.clone.uber.strategy;

import com.clone.uber.dto.RideRequestDto;

public interface RideFareCalculationStrategy {

    double calculateFate(RideRequestDto rideRequestDto);
}
