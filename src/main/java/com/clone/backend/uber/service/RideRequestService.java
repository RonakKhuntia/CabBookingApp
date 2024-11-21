package com.clone.backend.uber.service;

import com.clone.backend.uber.entity.RideRequest;

public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequestId);
}
