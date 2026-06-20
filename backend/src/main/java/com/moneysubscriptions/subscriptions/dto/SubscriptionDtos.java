package com.moneysubscriptions.subscriptions.dto;

import com.moneysubscriptions.shared.money.MoneyAmount;
import com.moneysubscriptions.subscriptions.model.Subscription;
import com.moneysubscriptions.subscriptions.model.SubscriptionStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public final class SubscriptionDtos {

    private SubscriptionDtos() {
    }

    public record UpsertRequest(
            @NotBlank(message = "Subscription name is required")
            @Size(max = 120, message = "Subscription name must be at most 120 characters")
            String name,
            @Size(max = 500, message = "Description must be at most 500 characters")
            String description,
            @NotNull(message = "Monthly cost is required")
            @DecimalMin(value = "0.01", inclusive = true, message = "Monthly cost must be greater than zero")
            BigDecimal monthlyCost,
            String currency,
            @NotNull(message = "Subscription type is required")
            UUID typeId,
            @NotNull(message = "Payment method is required")
            UUID paymentMethodId,
            @NotNull(message = "Status is required")
            SubscriptionStatus status,
            LocalDate startedAt,
            LocalDate cancelledAt
    ) {
        public MoneyAmount toMoneyAmount() {
            return MoneyAmount.of(monthlyCost, currency);
        }
    }

    public record Response(
            UUID id,
            String name,
            String description,
            String monthlyCost,
            String currency,
            SubscriptionStatus status,
            UUID typeId,
            String typeName,
            UUID paymentMethodId,
            String paymentMethodDisplayName,
            String paymentMethodNickname,
            LocalDate startedAt,
            LocalDate cancelledAt
    ) {
    }

    public static Response toResponse(Subscription subscription) {
        return new Response(
                subscription.getId(),
                subscription.getName(),
                subscription.getDescription(),
                subscription.getMonthlyCost().amount().toPlainString(),
                subscription.getMonthlyCost().currency(),
                subscription.getStatus(),
                subscription.getType().getId(),
                subscription.getType().getName(),
                subscription.getPaymentMethod().getId(),
                subscription.getPaymentMethod().getDisplayName(),
                subscription.getPaymentMethod().getNickname(),
                subscription.getStartedAt(),
                subscription.getCancelledAt()
        );
    }

    public static SubscriptionSummaryResponse toSummaryResponse(Subscription subscription) {
        return new SubscriptionSummaryResponse(
                subscription.getId(),
                subscription.getName(),
                subscription.getMonthlyCost().amount().toPlainString(),
                subscription.getMonthlyCost().currency(),
                subscription.getStatus(),
                new SubscriptionSummaryResponse.TypeSummary(
                        subscription.getType().getId(),
                        subscription.getType().getName()
                ),
                new SubscriptionSummaryResponse.PaymentMethodSummary(
                        subscription.getPaymentMethod().getId(),
                        subscription.getPaymentMethod().getKind(),
                        subscription.getPaymentMethod().getDisplayName(),
                        subscription.getPaymentMethod().getNickname()
                )
        );
    }
}
