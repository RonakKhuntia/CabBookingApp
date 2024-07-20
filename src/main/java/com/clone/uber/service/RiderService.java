package com.clone.uber.service;

import com.clone.uber.dto.DriverDto;
import com.clone.uber.dto.RideDto;
import com.clone.uber.dto.RideRequestDto;
import com.clone.uber.dto.RiderDto;
import com.clone.uber.entity.RideRequest;

import java.util.List;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);
    RideDto cancelRide(Long rideId);
    DriverDto rateDriver(Long rideId, Integer rating);
    RiderDto getMyProfile();
    List<RideDto> getAllMyRides();
}