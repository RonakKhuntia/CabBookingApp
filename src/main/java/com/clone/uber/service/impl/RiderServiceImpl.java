package com.clone.uber.service.impl;

import com.clone.uber.dto.DriverDto;
import com.clone.uber.dto.RideDto;
import com.clone.uber.dto.RideRequestDto;
import com.clone.uber.dto.RiderDto;
import com.clone.uber.entity.RideRequest;
import com.clone.uber.entity.Rider;
import com.clone.uber.entity.User;
import com.clone.uber.entity.enums.RideRequestStatus;
import com.clone.uber.repository.RideRequestRepository;
import com.clone.uber.repository.RiderRepository;
import com.clone.uber.service.RiderService;
import com.clone.uber.strategy.DriverMatchingStrategy;
import com.clone.uber.strategy.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideFareCalculationStrategy rideFareCalculationStrategy;
    private final DriverMatchingStrategy driverMatchingStrategy;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        Double fare = rideFareCalculationStrategy.calculateFate(rideRequest);
        rideRequest.setFare(fare);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        driverMatchingStrategy.findMatchingDriver(rideRequest);
        return modelMapper.map(savedRideRequest,RideRequestDto.class);
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

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }
}
