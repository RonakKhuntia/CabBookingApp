package com.clone.backend.uber.service.impl;

import com.clone.backend.uber.entity.WalletTransaction;
import com.clone.backend.uber.model.WalletTransactionDto;
import com.clone.backend.uber.repository.WalletTransactionRepository;
import com.clone.backend.uber.service.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }
}
