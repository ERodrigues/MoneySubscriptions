package com.moneysubscriptions.subscriptions.model;

import com.moneysubscriptions.shared.money.MoneyAmount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubscriptionEntityTest {

    @Test
    void shouldRequireCreditCardNickname() {
        PaymentMethod paymentMethod = new PaymentMethod(
                PaymentMethodKind.CREDIT_CARD,
                "Cartao principal",
                null,
                "Banco"
        );

        assertThrows(IllegalArgumentException.class, paymentMethod::validateState);
    }

    @Test
    void shouldAllowValidCreditCardNickname() {
        PaymentMethod paymentMethod = new PaymentMethod(
                PaymentMethodKind.CREDIT_CARD,
                "Cartao principal",
                "Nubank",
                "Nubank"
        );

        assertDoesNotThrow(paymentMethod::validateState);
    }

    @Test
    void shouldRejectCancelledDateBeforeStartedDate() {
        SubscriptionType type = new SubscriptionType("Streaming");
        PaymentMethod paymentMethod = new PaymentMethod(PaymentMethodKind.BOLETO, "Boleto", null, null);
        Subscription subscription = new Subscription(
                "Netflix",
                null,
                MoneyAmount.of(new BigDecimal("39.90"), "BRL"),
                SubscriptionStatus.ACTIVE,
                type,
                paymentMethod,
                LocalDate.of(2026, 6, 10),
                LocalDate.of(2026, 6, 9)
        );

        assertThrows(IllegalArgumentException.class, subscription::validateState);
    }
}
