package com.clone.uber.service;

import com.clone.uber.entity.Payment;
import com.clone.uber.entity.Ride;
import com.clone.uber.entity.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);
}
