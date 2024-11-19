package com.clone.backend.uber.service.impl;

import com.clone.backend.uber.dto.DriverDto;
import com.clone.backend.uber.dto.RideDto;
import com.clone.backend.uber.dto.RideRequestDto;
import com.clone.backend.uber.dto.RiderDto;
import com.clone.backend.uber.entity.RideRequest;
import com.clone.backend.uber.entity.Rider;
import com.clone.backend.uber.entity.User;
import com.clone.backend.uber.enums.RideRequestStatus;
import com.clone.backend.uber.repository.RideRequestRepository;
import com.clone.backend.uber.repository.RiderRepository;
import com.clone.backend.uber.service.RiderService;
import com.clone.backend.uber.strategy.DriverMatchingStrategy;
import com.clone.backend.uber.strategy.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideFareCalculationStrategy rideFareCalculationStrategy;
    private final DriverMatchingStrategy driverMatchingStrategy;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        Double fare = rideFareCalculationStrategy.calculateFare(rideRequest);
        rideRequest.setFare(fare);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        driverMatchingStrategy.findMatchingDrivers(rideRequest);
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
}
