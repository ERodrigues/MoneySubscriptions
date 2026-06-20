package com.moneysubscriptions.subscriptions.repository;

import com.moneysubscriptions.subscriptions.model.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType, UUID> {

    Optional<SubscriptionType> findByNameIgnoreCase(String name);
}
