package com.clone.backend.uber.model;

import com.clone.backend.uber.entity.User;
import com.clone.backend.uber.entity.WalletTransaction;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
public class WalletDto {
    private Long id;
    private User user;
    private Double balance;
    private List<WalletTransactionDto> transactions;
}
