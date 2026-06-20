package com.moneysubscriptions.subscriptions.dto;

import com.moneysubscriptions.subscriptions.model.SubscriptionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public final class SubscriptionTypeDtos {

    private SubscriptionTypeDtos() {
    }

    public record CreateRequest(
            @NotBlank(message = "Type name is required")
            @Size(max = 80, message = "Type name must be at most 80 characters")
            String name
    ) {
    }

    public record Response(
            UUID id,
            String name,
            boolean active
    ) {
    }

    public static Response toResponse(SubscriptionType type) {
        return new Response(type.getId(), type.getName(), type.isActive());
    }
}
