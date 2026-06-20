package com.moneysubscriptions.shared.money;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyAmountTest {

    @Test
    void shouldNormalizeCurrencyAndScale() {
        MoneyAmount amount = MoneyAmount.of(new BigDecimal("39.9"), "brl");

        assertEquals(new BigDecimal("39.90"), amount.amount());
        assertEquals("BRL", amount.currency());
    }

    @Test
    void shouldRejectZeroOrNegativeAmounts() {
        assertThrows(IllegalArgumentException.class, () -> MoneyAmount.of(BigDecimal.ZERO, "BRL"));
        assertThrows(IllegalArgumentException.class, () -> MoneyAmount.of(new BigDecimal("-1.00"), "BRL"));
    }

    @Test
    void shouldAddMoneyWithTheSameCurrency() {
        MoneyAmount result = MoneyAmount.of(new BigDecimal("10.00"), "BRL")
                .add(MoneyAmount.of(new BigDecimal("2.50"), "BRL"));

        assertEquals(new BigDecimal("12.50"), result.amount());
        assertEquals("BRL", result.currency());
    }
}
