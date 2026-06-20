package com.moneysubscriptions.subscriptions.repository;

import com.moneysubscriptions.subscriptions.model.PaymentMethod;
import com.moneysubscriptions.subscriptions.model.PaymentMethodKind;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID> {

    List<PaymentMethod> findAllByKindOrderByDisplayNameAsc(PaymentMethodKind kind);
}
