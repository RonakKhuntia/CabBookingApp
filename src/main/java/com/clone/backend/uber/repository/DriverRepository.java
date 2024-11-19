package com.clone.backend.uber.repository;

import com.clone.backend.uber.entity.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query(value = "SELECT d.*, ST_Distance(d.currentLocation, :pickupLocation) AS distance " +
            "FROM Driver As d " +
            "WHERE d.available = true " +
            "AND ST_DWithin(d.currentLocation, :pickupLocation, 10000) " +
            "ORDER BY distance " +
            "LIMIT 10", nativeQuery = true)
    List<Driver> findMatchingDrivers(Point pickupLocation);

}
