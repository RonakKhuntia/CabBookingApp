package com.clone.backend.uber.service.impl;

import com.clone.backend.uber.entity.*;
import com.clone.backend.uber.enums.RideStatus;
import com.clone.backend.uber.exception.ResourceNotFoundException;
import com.clone.backend.uber.model.DriverDto;
import com.clone.backend.uber.model.RideDto;
import com.clone.backend.uber.model.RideRequestDto;
import com.clone.backend.uber.model.RiderDto;
import com.clone.backend.uber.enums.RideRequestStatus;
import com.clone.backend.uber.repository.RideRequestRepository;
import com.clone.backend.uber.repository.RiderRepository;
import com.clone.backend.uber.service.DriverService;
import com.clone.backend.uber.service.RideService;
import com.clone.backend.uber.service.RiderService;
import com.clone.backend.uber.strategy.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final DriverService driverService;
    private final RideService rideService;

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);
        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        List<Driver> drivers = rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDrivers(rideRequest);
        // TODO : Send notification to all the drivers about this ride request
        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Rider rider = getCurrentRider();
        Ride ride = rideService.getRideById(rideId);
        if(rider.equals(ride.getRider())) {
            throw new RuntimeException("Rider does not own this ride");
        }
        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride cannot be cancelled, invalid status : " + ride.getRideStatus());
        }
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(), true);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        Rider currentRider = getCurrentRider();
        return modelMapper.map(currentRider, RiderDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider = getCurrentRider();
        return rideService.getAllRidesOfRider(currentRider, pageRequest)
                .map(ride -> modelMapper.map(ride, RideDto.class));

    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider
                .builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        // TODO : implement Spring security
        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Rider not found with id: %s", 1)
        ));
    }

}
