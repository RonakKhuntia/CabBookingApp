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
import com.clone.backend.uber.service.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final PaymentService paymentService;
    private final RatingService ratingService;

    @Override
    public Driver createNewDriver(Driver driver) {
        return driverRepository.save(driver);
    }

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
        Driver savedDriver = updateDriverAvailability(currentDriver, false);
        Ride ride = rideService.createNewRide(rideRequest, savedDriver);
        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if (!driver.equals(ride.getDriver())) {
            throw new RuntimeException("Driver cannot start a ride as he had not accepted it earlier");
        }
        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride cannot be cancelled, invalid status : " + ride.getRideStatus());
        }
        rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        updateDriverAvailability(driver, true);
        return modelMapper.map(ride, RideDto.class);
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
        paymentService.createNewPayment(savedRide);
        ratingService.createNewRating(savedRide);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    @Transactional
    public RideDto endRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if (!driver.equals(ride.getDriver())) {
            throw new RuntimeException("Driver cannot start a ride as he had not accepted it earlier");
        }
        if (!ride.getRideStatus().equals(RideStatus.ONGOING)) {
            throw new RuntimeException(String.format("Ride cannot be ended as status is %s", ride.getRideStatus()));
        }
        ride.setEndedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
        updateDriverAvailability(driver, true);
        paymentService.processPayment(ride);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if (!driver.equals(ride.getDriver())) {
            throw new RuntimeException("Driver is not the owner of this ride");
        }
        if (!ride.getRideStatus().equals(RideStatus.ENDED)) {
            throw new RuntimeException("Ride status is not ended hence cannot give rating, status : " + ride.getRideStatus());
        }
        return ratingService.rateRider(ride, rating);
    }

    @Override
    public DriverDto getMyProfile() {
        Driver currentDriver = getCurrentDriver();
        return modelMapper.map(currentDriver, DriverDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(currentDriver, pageRequest)
                .map(ride -> modelMapper.map(ride, RideDto.class));
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Driver not found with id %s", 2)));
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean availability) {
        driver.setAvailable(availability);
        return driverRepository.save(driver);
    }

}
