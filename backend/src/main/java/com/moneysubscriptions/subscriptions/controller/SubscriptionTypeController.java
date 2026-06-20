package com.moneysubscriptions.subscriptions.controller;

import com.moneysubscriptions.subscriptions.dto.SubscriptionTypeDtos;
import com.moneysubscriptions.subscriptions.service.SubscriptionTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscription-types")
public class SubscriptionTypeController {

    private final SubscriptionTypeService subscriptionTypeService;

    public SubscriptionTypeController(SubscriptionTypeService subscriptionTypeService) {
        this.subscriptionTypeService = subscriptionTypeService;
    }

    @GetMapping
    public List<SubscriptionTypeDtos.Response> listAll() {
        return subscriptionTypeService.listAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionTypeDtos.Response create(@Valid @RequestBody SubscriptionTypeDtos.CreateRequest request) {
        return subscriptionTypeService.create(request);
    }
}
