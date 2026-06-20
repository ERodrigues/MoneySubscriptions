package com.moneysubscriptions.subscriptions.model;

import com.moneysubscriptions.shared.money.MoneyAmount;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(length = 500)
    private String description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "monthly_cost", nullable = false, precision = 12, scale = 2)),
            @AttributeOverride(name = "currency", column = @Column(name = "currency", nullable = false, length = 3))
    })
    private MoneyAmount monthlyCost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private SubscriptionStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id", nullable = false)
    private SubscriptionType type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "started_at")
    private LocalDate startedAt;

    @Column(name = "cancelled_at")
    private LocalDate cancelledAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected Subscription() {
    }

    public Subscription(
            String name,
            String description,
            MoneyAmount monthlyCost,
            SubscriptionStatus status,
            SubscriptionType type,
            PaymentMethod paymentMethod,
            LocalDate startedAt,
            LocalDate cancelledAt
    ) {
        this.name = name;
        this.description = description;
        this.monthlyCost = monthlyCost;
        this.status = status;
        this.type = type;
        this.paymentMethod = paymentMethod;
        this.startedAt = startedAt;
        this.cancelledAt = cancelledAt;
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
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Subscription name is required");
        }
        name = name.trim();
        description = description == null || description.isBlank() ? null : description.trim();
        if (monthlyCost == null) {
            throw new IllegalArgumentException("Subscription monthly cost is required");
        }
        if (status == null) {
            throw new IllegalArgumentException("Subscription status is required");
        }
        if (type == null) {
            throw new IllegalArgumentException("Subscription type is required");
        }
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method is required");
        }
        if (cancelledAt != null && startedAt != null && cancelledAt.isBefore(startedAt)) {
            throw new IllegalArgumentException("Cancelled date cannot be before start date");
        }
    }
}
