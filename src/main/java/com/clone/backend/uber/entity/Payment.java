package com.clone.backend.uber.entity;

import com.clone.backend.uber.enums.PaymentMethod;
import com.clone.backend.uber.enums.PaymentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @OneToOne(fetch = FetchType.LAZY)
    private Ride ride;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private Double amount;
    @CreationTimestamp
    private LocalDateTime paymentTime;
}
