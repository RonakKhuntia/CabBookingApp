package com.clone.backend.uber.service;

import com.clone.backend.uber.entity.Driver;
import com.clone.backend.uber.entity.Ride;
import com.clone.backend.uber.entity.Rider;
import com.clone.backend.uber.model.DriverDto;
import com.clone.backend.uber.model.RiderDto;

public interface RatingService {
    DriverDto rateDriver(Ride ride, Integer rating);
    RiderDto rateRider(Ride ride, Integer rating);
    void createNewRating(Ride ride);
}
