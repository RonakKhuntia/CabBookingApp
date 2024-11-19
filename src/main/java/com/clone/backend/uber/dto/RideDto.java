package com.clone.backend.uber.dto;

import com.clone.backend.uber.entity.Rider;
import com.clone.backend.uber.enums.PaymentMethod;
import com.clone.backend.uber.enums.RideStatus;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDto {
    private Long id;
    private Point pickupLocation;
    private Point dropOffLocation;
    private LocalDateTime createdTime;
    private Rider rider;
    private String otp;
    private DriverDto driver;
    private PaymentMethod paymentMethod;
    private RideStatus rideRequestStatus;
    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
