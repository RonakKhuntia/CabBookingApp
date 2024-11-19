package com.clone.backend.uber.service;

import com.clone.backend.uber.dto.DriverDto;
import com.clone.backend.uber.dto.RideDto;
import com.clone.backend.uber.dto.RiderDto;

import java.util.List;

public interface DriverService {
    RideDto acceptRide(Long rideId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId, Integer rating);

    DriverDto getMyProfile();

    List<RideDto> getMyRides();
}
