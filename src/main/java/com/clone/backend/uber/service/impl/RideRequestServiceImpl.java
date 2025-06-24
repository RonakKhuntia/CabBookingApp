package com.clone.backend.uber.service.impl;

import com.clone.backend.uber.entity.RideRequest;
import com.clone.backend.uber.exception.ResourceNotFoundException;
import com.clone.backend.uber.repository.RideRequestRepository;
import com.clone.backend.uber.service.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository
                .findById(rideRequestId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Ride Request Not Found with id %s",
                        rideRequestId)));
    }

    @Override
    public void saveRideRequestToRepository(RideRequest rideRequest) {
        findRideRequestById(rideRequest.getId());
        rideRequestRepository.save(rideRequest);
    }

}
