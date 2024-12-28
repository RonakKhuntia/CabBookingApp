package com.clone.backend.uber.service;

import com.clone.backend.uber.model.DriverDto;
import com.clone.backend.uber.model.RideDto;
import com.clone.backend.uber.model.RideRequestDto;
import com.clone.backend.uber.model.RiderDto;
import com.clone.backend.uber.entity.Rider;
import com.clone.backend.uber.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getCurrentRider();
}
