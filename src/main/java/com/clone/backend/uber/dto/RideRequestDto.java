package com.clone.backend.uber.dto;

import com.clone.backend.uber.enums.PaymentMethod;
import com.clone.backend.uber.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {
    private Long id;
    private Point pickupLocation;
    private Point dropOffLocation;
    private LocalDateTime requestTime;
    private RiderDto rider;
    private PaymentMethod paymentMethod;
    private RideRequestStatus rideRequestStatus;
}
