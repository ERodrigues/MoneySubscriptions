package com.moneysubscriptions.subscriptions.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "subscription_types")
public class SubscriptionType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(nullable = false, length = 80, unique = true)
    private String name;

    @Column(nullable = false)
    private boolean active = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected SubscriptionType() {
    }

    public SubscriptionType(String name) {
        this.name = normalizeName(name);
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
        name = normalizeName(name);
    }

    private String normalizeName(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Subscription type name is required");
        }
        return value.trim();
    }
}
