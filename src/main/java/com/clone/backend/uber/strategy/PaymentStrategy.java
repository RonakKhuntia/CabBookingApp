package com.clone.backend.uber.strategy;

import com.clone.backend.uber.entity.Payment;

public interface PaymentStrategy {
    Double PLATFORM_COMISSION = 0.3;
    void processPayment(Payment payment);
}
