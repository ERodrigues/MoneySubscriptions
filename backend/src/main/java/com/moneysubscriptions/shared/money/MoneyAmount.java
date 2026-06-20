package com.moneysubscriptions.shared.money;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Embeddable
public class MoneyAmount {

    @NotNull
    @Column(name = "monthly_cost", nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @NotBlank
    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    protected MoneyAmount() {
    }

    public MoneyAmount(BigDecimal amount, String currency) {
        this.amount = normalizeAmount(amount);
        this.currency = normalizeCurrency(currency);
    }

    public static MoneyAmount of(BigDecimal amount, String currency) {
        return new MoneyAmount(amount, currency);
    }

    public BigDecimal amount() {
        return amount;
    }

    public String currency() {
        return currency;
    }

    public MoneyAmount add(MoneyAmount other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("Money values must share the same currency");
        }
        return new MoneyAmount(amount.add(other.amount), currency);
    }

    private static BigDecimal normalizeAmount(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("Amount is required");
        }
        BigDecimal normalized = value.setScale(2, RoundingMode.HALF_UP);
        if (normalized.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        return normalized;
    }

    private static String normalizeCurrency(String value) {
        if (value == null || value.isBlank()) {
            return "BRL";
        }
        String normalized = value.trim().toUpperCase();
        if (normalized.length() != 3) {
            throw new IllegalArgumentException("Currency must use a 3-letter code");
        }
        return normalized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MoneyAmount that)) {
            return false;
        }
        return Objects.equals(amount, that.amount) && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}
