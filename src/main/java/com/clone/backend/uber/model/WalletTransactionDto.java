package com.clone.backend.uber.model;

import com.clone.backend.uber.entity.Ride;
import com.clone.backend.uber.entity.Wallet;
import com.clone.backend.uber.enums.TransactionMethod;
import com.clone.backend.uber.enums.TransactionType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class WalletTransactionDto {
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;
    private RideDto rideDto;
    private String transactionId;
    private LocalDateTime timeStamp;
    private WalletDto walletDto;
}
