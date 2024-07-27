package com.clone.uber.strategy;

import com.clone.uber.dto.RideRequestDto;
import com.clone.uber.entity.Driver;
import com.clone.uber.entity.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

    //find matching driver
    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
