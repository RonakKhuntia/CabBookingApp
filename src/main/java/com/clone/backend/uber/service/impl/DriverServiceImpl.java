package com.clone.backend.uber.service.impl;

import com.clone.backend.uber.entity.Driver;
import com.clone.backend.uber.entity.Ride;
import com.clone.backend.uber.entity.RideRequest;
import com.clone.backend.uber.enums.RideRequestStatus;
import com.clone.backend.uber.enums.RideStatus;
import com.clone.backend.uber.exception.ResourceNotFoundException;
import com.clone.backend.uber.model.DriverDto;
import com.clone.backend.uber.model.RideDto;
import com.clone.backend.uber.model.RiderDto;
import com.clone.backend.uber.repository.DriverRepository;
import com.clone.backend.uber.service.DriverService;
import com.clone.backend.uber.service.RideRequestService;
import com.clone.backend.uber.service.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final RideRequestService rideRequestService;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        if (!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
            throw new RuntimeException(String.format("RideRequest cannot be accepted, status is %s",
                    rideRequest.getRideRequestStatus()));
        }
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        Driver currentDriver = getCurrentDriver();
        if (!currentDriver.getAvailable()) {
            throw new RuntimeException("Driver cannot accept ride due to unavailability");
        }
        currentDriver.setAvailable(false);
        Driver savedDriver = driverRepository.save(currentDriver);
        Ride ride = rideService.createNewRide(rideRequest, savedDriver);
        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto startRide(Long rideId, String otp) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if (!driver.equals(ride.getDriver())) {
            throw new RuntimeException("Driver cannot start a ride as he had not accepted it earlier");
        }
        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException(String.format("Ride cannot be started as status is %s", ride.getRideStatus()));
        }
        if (!ride.getOtp().equals(otp)) {
            throw new RuntimeException(String.format("OTP %s is not valid", otp));
        }
        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getMyRides() {
        return List.of();
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Driver not found with id %s", 2)));
    }

}
