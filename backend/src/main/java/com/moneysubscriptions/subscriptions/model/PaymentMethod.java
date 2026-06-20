package com.moneysubscriptions.subscriptions.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "payment_methods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private PaymentMethodKind kind;

    @Column(name = "display_name", nullable = false, length = 100)
    private String displayName;

    @Column(length = 80)
    private String nickname;

    @Column(name = "institution_name", length = 100)
    private String institutionName;

    @Column(nullable = false)
    private boolean active = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected PaymentMethod() {
    }

    public PaymentMethod(PaymentMethodKind kind, String displayName, String nickname, String institutionName) {
        this.kind = kind;
        this.displayName = displayName;
        this.nickname = nickname;
        this.institutionName = institutionName;
    }

    @PrePersist
    void prePersist() {
        validateState();
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        validateState();
        updatedAt = Instant.now();
    }

    void validateState() {
        if (kind == null) {
            throw new IllegalArgumentException("Payment method kind is required");
        }
        if (displayName == null || displayName.isBlank()) {
            throw new IllegalArgumentException("Payment method display name is required");
        }
        displayName = displayName.trim();
        nickname = normalizeOptional(nickname);
        institutionName = normalizeOptional(institutionName);
        if (kind == PaymentMethodKind.CREDIT_CARD && nickname == null) {
            throw new IllegalArgumentException("Credit card payment methods require a nickname");
        }
    }

    private String normalizeOptional(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }
}
