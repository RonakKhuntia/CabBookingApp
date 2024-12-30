package com.clone.backend.uber.service;

import com.clone.backend.uber.entity.Payment;
import com.clone.backend.uber.entity.Ride;
import com.clone.backend.uber.enums.PaymentStatus;

public interface PaymentService {
    void processPayment(Ride ride);
    Payment createNewPayment(Ride ride);
    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
