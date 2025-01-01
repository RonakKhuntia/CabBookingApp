package com.clone.backend.uber.repository;

import com.clone.backend.uber.entity.Rider;
import com.clone.backend.uber.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
    Optional<Rider> findByUser(User user);
}
