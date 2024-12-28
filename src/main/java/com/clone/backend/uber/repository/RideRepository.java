package com.clone.backend.uber.repository;

import com.clone.backend.uber.entity.Driver;
import com.clone.backend.uber.entity.Ride;
import com.clone.backend.uber.entity.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    Page<Ride> findByRider(Rider rider, Pageable pageRequest);
    Page<Ride> findByDriver(Driver driver, Pageable pageRequest);
}
