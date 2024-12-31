package com.clone.backend.uber.entity;

import com.clone.backend.uber.enums.TransactionMethod;
import com.clone.backend.uber.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(indexes = {
        @Index(name = "idx_wallet_transaction_wallet", columnList = "wallet_id"),
        @Index(name = "idx_wallet_transaction_ride", columnList = "ride_id")
})
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;
    @ManyToOne
    private Ride ride;
    private String transactionId;
    @CreationTimestamp
    private LocalDateTime timeStamp;
    @ManyToOne
    private Wallet wallet;
}
