package com.clone.backend.uber.strategy.impl;

import com.clone.backend.uber.entity.Driver;
import com.clone.backend.uber.entity.Payment;
import com.clone.backend.uber.enums.PaymentStatus;
import com.clone.backend.uber.enums.TransactionMethod;
import com.clone.backend.uber.repository.PaymentRepository;
import com.clone.backend.uber.service.PaymentService;
import com.clone.backend.uber.service.WalletService;
import com.clone.backend.uber.strategy.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Double platformComission = payment.getAmount() * PLATFORM_COMISSION;
        walletService.deductMoneyFromWallet(driver.getUser(), platformComission, null, payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
