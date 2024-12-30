package com.clone.backend.uber.service.impl;

import com.clone.backend.uber.entity.Payment;
import com.clone.backend.uber.entity.Ride;
import com.clone.backend.uber.enums.PaymentStatus;
import com.clone.backend.uber.exception.ResourceNotFoundException;
import com.clone.backend.uber.repository.PaymentRepository;
import com.clone.backend.uber.service.PaymentService;
import com.clone.backend.uber.strategy.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found for ride id " + ride.getId()));
        paymentStrategyManager.getPaymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder()
                .ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);
    }

}
