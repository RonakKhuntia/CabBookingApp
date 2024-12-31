package com.clone.backend.uber.service.impl;

import com.clone.backend.uber.entity.Driver;
import com.clone.backend.uber.entity.Rating;
import com.clone.backend.uber.entity.Ride;
import com.clone.backend.uber.entity.Rider;
import com.clone.backend.uber.exception.ResourceNotFoundException;
import com.clone.backend.uber.model.DriverDto;
import com.clone.backend.uber.model.RiderDto;
import com.clone.backend.uber.repository.DriverRepository;
import com.clone.backend.uber.repository.RatingRepository;
import com.clone.backend.uber.repository.RiderRepository;
import com.clone.backend.uber.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriverDto rateDriver(Ride ride, Integer rating) {
        Rating rating1 = ratingRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Rating not found for ride id : " + ride.getId()));
        if (rating1.getDriverRating() != null) {
            throw new RuntimeException("Driver has already been rated, cannot give rating again!");
        }
        rating1.setDriverRating(rating);
        ratingRepository.save(rating1);
        Driver driver = ride.getDriver();
        List<Rating> ratings = ratingRepository.findByDriver(driver);
        Double newDriverRating = ratings.stream()
                .mapToDouble(Rating::getDriverRating)
                .average()
                .orElse(0.0);
        driver.setRating(newDriverRating);
        Driver savedDriver = driverRepository.save(driver);
        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public RiderDto rateRider(Ride ride, Integer rating) {
        Rating rating1 = ratingRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Rating not found for ride id : " + ride.getId()));
        if (rating1.getRiderRating() != null) {
            throw new RuntimeException("Rider has already been rated, cannot give rating again!");
        }
        rating1.setRiderRating(rating);
        ratingRepository.save(rating1);
        Rider rider = ride.getRider();
        List<Rating> ratings = ratingRepository.findByRider(rider);
        Double newRiderRating = ratings.stream()
                .mapToDouble(Rating::getRiderRating)
                .average()
                .orElse(0.0);
        rider.setRating(newRiderRating);
        Rider savedRider = riderRepository.save(rider);
        return modelMapper.map(savedRider, RiderDto.class);
    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating = Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();
        ratingRepository.save(rating);
    }
}
