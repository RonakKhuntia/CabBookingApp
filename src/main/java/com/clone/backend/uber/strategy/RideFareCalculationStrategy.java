package com.clone.backend.uber.strategy;

import com.clone.backend.uber.entity.RideRequest;

public interface RideFareCalculationStrategy {
    double RIDE_FARE_MULTIPLIER = 10;

    double calculateFare(RideRequest rideRequest);
}
