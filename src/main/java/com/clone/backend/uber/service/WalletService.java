package com.clone.backend.uber.service;

import com.clone.backend.uber.entity.Ride;
import com.clone.backend.uber.entity.User;
import com.clone.backend.uber.entity.Wallet;
import com.clone.backend.uber.enums.TransactionMethod;

public interface WalletService {
    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);
    void withdrawMoneyFromWallet();
    Wallet findWalletById(Long walletId);
    Wallet createNewWallet(User user);
    Wallet findByUser(User user);
    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);
}
