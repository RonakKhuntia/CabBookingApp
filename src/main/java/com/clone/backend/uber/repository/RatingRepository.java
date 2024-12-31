package com.clone.backend.uber.repository;

import com.clone.backend.uber.entity.Driver;
import com.clone.backend.uber.entity.Rating;
import com.clone.backend.uber.entity.Ride;
import com.clone.backend.uber.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);
    Optional<Rating> findByRide(Ride ride);
}
