package com.clone.uber.strategy;

import com.clone.uber.dto.RideRequestDto;
import com.clone.uber.entity.RideRequest;

public interface RideFareCalculationStrategy {

    double RIDE_FARE_MULTIPLIER = 10;

    //calculateFare
    double calculateFare(RideRequest rideRequest);
}
