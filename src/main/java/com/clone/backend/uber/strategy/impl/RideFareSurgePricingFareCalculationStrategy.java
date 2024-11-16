package com.clone.backend.uber.strategy.impl;

import com.clone.backend.uber.dto.RideRequestDto;
import com.clone.backend.uber.strategy.RideFareCalculationStrategy;
import org.springframework.stereotype.Service;

@Service
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
