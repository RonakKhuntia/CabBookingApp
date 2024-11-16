package com.clone.backend.uber.service;

import com.clone.backend.uber.dto.DriverDto;
import com.clone.backend.uber.dto.RideDto;
import com.clone.backend.uber.dto.RideRequestDto;
import com.clone.backend.uber.dto.RiderDto;

import java.util.List;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);
    RideDto cancelRide(Long rideId);
    DriverDto rateDriver(Long rideId, Integer rating);
    RiderDto getMyProfile();
    List<RideDto> getMyRides();
}
