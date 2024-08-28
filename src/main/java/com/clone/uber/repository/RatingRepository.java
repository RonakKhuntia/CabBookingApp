package com.clone.uber.repository;


import com.clone.uber.entity.Driver;
import com.clone.uber.entity.Rating;
import com.clone.uber.entity.Ride;
import com.clone.uber.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);
}