package com.clone.backend.uber.service.impl;

import com.clone.backend.uber.entity.RideRequest;
import com.clone.backend.uber.model.RideRequestDto;
import com.clone.backend.uber.entity.Driver;
import com.clone.backend.uber.entity.Ride;
import com.clone.backend.uber.enums.RideStatus;
import com.clone.backend.uber.repository.RideRepository;
import com.clone.backend.uber.service.RideRequestService;
import com.clone.backend.uber.service.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;

    @Override
    public Ride getRideById(Long rideId) {
        return null;
    }

    @Override
    public void matchWithDrivers(RideRequestDto rideRequestDto) {

    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        Ride ride = modelMapper.map(rideRequest, Ride.class);
        ride.setDriver(driver);
        ride.setOtp(generateOTP());
        rideRequestService.saveRideRequestToRepository(rideRequest);
        return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Long rideId, RideStatus rideStatus) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRideOfDriver(Long driverId, PageRequest pageRequest) {
        return null;
    }

    private String generateOTP() {
        return String.valueOf(new Random().nextInt(9999 - 1000 + 1));
    }

}
