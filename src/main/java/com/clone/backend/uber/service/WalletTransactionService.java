package com.clone.backend.uber.service;

import com.clone.backend.uber.entity.WalletTransaction;
import com.clone.backend.uber.model.WalletTransactionDto;

public interface WalletTransactionService {
    void createNewWalletTransaction(WalletTransaction walletTransaction);
}
