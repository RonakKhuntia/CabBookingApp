package com.clone.uber.entity;

import com.clone.uber.entity.enums.PaymentMethod;
import com.clone.uber.entity.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Data
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point pickupLocation;
    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point dropOffLocation;
    @CreationTimestamp
    private LocalDateTime requestedTime;
    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    private RideRequestStatus rideRequestStatus;
}