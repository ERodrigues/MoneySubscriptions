package com.moneysubscriptions.subscriptions.controller;

import com.moneysubscriptions.subscriptions.dto.SubscriptionDtos;
import com.moneysubscriptions.subscriptions.dto.SubscriptionSummaryResponse;
import com.moneysubscriptions.subscriptions.model.SubscriptionStatus;
import com.moneysubscriptions.subscriptions.service.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public List<SubscriptionSummaryResponse> list(@RequestParam(required = false) SubscriptionStatus status) {
        return subscriptionService.list(status);
    }

    @GetMapping("/{id}")
    public SubscriptionDtos.Response getById(@PathVariable UUID id) {
        return subscriptionService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionDtos.Response create(@Valid @RequestBody SubscriptionDtos.UpsertRequest request) {
        return subscriptionService.create(request);
    }

    @PutMapping("/{id}")
    public SubscriptionDtos.Response update(@PathVariable UUID id, @Valid @RequestBody SubscriptionDtos.UpsertRequest request) {
        return subscriptionService.update(id, request);
    }
}
