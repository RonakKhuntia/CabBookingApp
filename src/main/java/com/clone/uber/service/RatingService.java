package com.clone.uber.service;

import com.clone.uber.dto.DriverDto;
import com.clone.uber.dto.RiderDto;
import com.clone.uber.entity.Ride;

public interface RatingService {

    DriverDto rateDriver(Ride ride, Integer rating);
    RiderDto rateRider(Ride ride, Integer rating);

    void createNewRating(Ride ride);
}

