package com.moneysubscriptions.subscriptions.service;

import com.moneysubscriptions.subscriptions.dto.PaymentMethodDtos;
import com.moneysubscriptions.subscriptions.model.PaymentMethod;
import com.moneysubscriptions.subscriptions.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Transactional(readOnly = true)
    public List<PaymentMethodDtos.Response> listAll() {
        return paymentMethodRepository.findAll()
                .stream()
                .map(PaymentMethodDtos::toResponse)
                .toList();
    }

    @Transactional
    public PaymentMethodDtos.Response create(PaymentMethodDtos.CreateRequest request) {
        PaymentMethod paymentMethod = new PaymentMethod(
                request.kind(),
                request.displayName(),
                request.nickname(),
                request.institutionName()
        );
        paymentMethod.validateState();
        return PaymentMethodDtos.toResponse(paymentMethodRepository.save(paymentMethod));
    }
}
