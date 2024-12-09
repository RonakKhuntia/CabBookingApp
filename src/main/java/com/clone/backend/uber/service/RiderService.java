package com.clone.backend.uber.service;

import com.clone.backend.uber.model.DriverDto;
import com.clone.backend.uber.model.RideDto;
import com.clone.backend.uber.model.RideRequestDto;
import com.clone.backend.uber.model.RiderDto;
import com.clone.backend.uber.entity.Rider;
import com.clone.backend.uber.entity.User;

import java.util.List;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    List<RideDto> getMyRides();

    Rider createNewRider(User user);

    Rider getCurrentRider();
}
