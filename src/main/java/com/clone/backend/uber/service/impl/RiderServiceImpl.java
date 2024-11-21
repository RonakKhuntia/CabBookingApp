package com.clone.backend.uber.service.impl;

import com.clone.backend.uber.entity.Driver;
import com.clone.backend.uber.exception.ResourceNotFoundException;
import com.clone.backend.uber.model.DriverDto;
import com.clone.backend.uber.model.RideDto;
import com.clone.backend.uber.model.RideRequestDto;
import com.clone.backend.uber.model.RiderDto;
import com.clone.backend.uber.entity.RideRequest;
import com.clone.backend.uber.entity.Rider;
import com.clone.backend.uber.entity.User;
import com.clone.backend.uber.enums.RideRequestStatus;
import com.clone.backend.uber.repository.RideRequestRepository;
import com.clone.backend.uber.repository.RiderRepository;
import com.clone.backend.uber.service.RiderService;
import com.clone.backend.uber.strategy.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    @Override
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
    public List<RideDto> getMyRides() {
        return List.of();
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

    public Rider getCurrentRider() {
        // TODO : implement Spring security
        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Rider not found with id: %s", 1)
        ));
    }

}
