package com.clone.backend.uber.strategy.impl;

import com.clone.backend.uber.entity.Driver;
import com.clone.backend.uber.entity.Payment;
import com.clone.backend.uber.entity.Rider;
import com.clone.backend.uber.enums.PaymentStatus;
import com.clone.backend.uber.enums.TransactionMethod;
import com.clone.backend.uber.repository.PaymentRepository;
import com.clone.backend.uber.service.PaymentService;
import com.clone.backend.uber.service.WalletService;
import com.clone.backend.uber.strategy.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();
        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(), null, payment.getRide(), TransactionMethod.RIDE);
        double driversCut = payment.getAmount() * (1 - PLATFORM_COMISSION);
        walletService.addMoneyToWallet(driver.getUser(), driversCut, null, payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }

}
