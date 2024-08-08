package com.clone.uber.strategy;

import com.clone.uber.entity.Payment;

public interface PaymentStrategy {
    Double PLATFORM_COMMISSION = 0.3;
    void processPayment(Payment payment);

}
