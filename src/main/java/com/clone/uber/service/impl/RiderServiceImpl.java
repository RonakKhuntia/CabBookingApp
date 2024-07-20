package com.clone.uber.service.impl;

import com.clone.uber.dto.DriverDto;
import com.clone.uber.dto.RideDto;
import com.clone.uber.dto.RideRequestDto;
import com.clone.uber.dto.RiderDto;
import com.clone.uber.service.RiderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderServiceImpl implements RiderService {
    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        return null;
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return null;
    }
}
