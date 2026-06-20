package com.moneysubscriptions.subscriptions.repository;

import com.moneysubscriptions.subscriptions.model.Subscription;
import com.moneysubscriptions.subscriptions.model.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

    List<Subscription> findAllByStatusOrderByNameAsc(SubscriptionStatus status);

    @Query("""
            select s.type.id as groupId, s.type.name as label, sum(s.monthlyCost.amount) as subtotal
            from Subscription s
            where s.status = com.moneysubscriptions.subscriptions.model.SubscriptionStatus.ACTIVE
            group by s.type.id, s.type.name
            order by s.type.name asc
            """)
    List<TypeGroupProjection> sumActiveByType();

    @Query("""
            select s.paymentMethod.id as groupId,
                   s.paymentMethod.displayName as label,
                   sum(s.monthlyCost.amount) as subtotal
            from Subscription s
            where s.status = com.moneysubscriptions.subscriptions.model.SubscriptionStatus.ACTIVE
            group by s.paymentMethod.id, s.paymentMethod.displayName
            order by s.paymentMethod.displayName asc
            """)
    List<PaymentMethodGroupProjection> sumActiveByPaymentMethod();

    interface TypeGroupProjection {
        UUID getGroupId();
        String getLabel();
        BigDecimal getSubtotal();
    }

    interface PaymentMethodGroupProjection {
        UUID getGroupId();
        String getLabel();
        BigDecimal getSubtotal();
    }
}
