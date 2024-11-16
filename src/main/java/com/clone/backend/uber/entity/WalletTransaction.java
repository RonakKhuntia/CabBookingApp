package com.clone.backend.uber.entity;

import com.clone.backend.uber.enums.TransactionMethod;
import com.clone.backend.uber.enums.TransactionType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;
    @OneToOne
    private Ride ride;
    private String transactionId;
    @CreationTimestamp
    private LocalDateTime timeStamp;
    @ManyToOne
    private Wallet wallet;
}
