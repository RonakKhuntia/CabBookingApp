package com.clone.backend.uber.strategy;

import com.clone.backend.uber.enums.PaymentMethod;
import com.clone.backend.uber.strategy.impl.CashPaymentStrategy;
import com.clone.backend.uber.strategy.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy getPaymentStrategy(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
        };
    }

}
