package com.moneysubscriptions.subscriptions.dto;

import com.moneysubscriptions.subscriptions.model.PaymentMethod;
import com.moneysubscriptions.subscriptions.model.PaymentMethodKind;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public final class PaymentMethodDtos {

    private PaymentMethodDtos() {
    }

    public record CreateRequest(
            @NotNull(message = "Payment method kind is required")
            PaymentMethodKind kind,
            @NotBlank(message = "Display name is required")
            @Size(max = 100, message = "Display name must be at most 100 characters")
            String displayName,
            @Size(max = 80, message = "Nickname must be at most 80 characters")
            String nickname,
            @Size(max = 100, message = "Institution name must be at most 100 characters")
            String institutionName
    ) {
    }

    public record Response(
            UUID id,
            PaymentMethodKind kind,
            String displayName,
            String nickname,
            String institutionName,
            boolean active
    ) {
    }

    public static Response toResponse(PaymentMethod paymentMethod) {
        return new Response(
                paymentMethod.getId(),
                paymentMethod.getKind(),
                paymentMethod.getDisplayName(),
                paymentMethod.getNickname(),
                paymentMethod.getInstitutionName(),
                paymentMethod.isActive()
        );
    }
}
