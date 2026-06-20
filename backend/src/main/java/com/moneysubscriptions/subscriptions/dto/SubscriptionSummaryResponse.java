package com.moneysubscriptions.subscriptions.dto;

import com.moneysubscriptions.subscriptions.model.PaymentMethodKind;
import com.moneysubscriptions.subscriptions.model.SubscriptionStatus;

import java.util.UUID;

public record SubscriptionSummaryResponse(
        UUID id,
        String name,
        String monthlyCost,
        String currency,
        SubscriptionStatus status,
        TypeSummary type,
        PaymentMethodSummary paymentMethod
) {
    public record TypeSummary(UUID id, String name) {
    }

    public record PaymentMethodSummary(
            UUID id,
            PaymentMethodKind kind,
            String displayName,
            String nickname
    ) {
    }
}
