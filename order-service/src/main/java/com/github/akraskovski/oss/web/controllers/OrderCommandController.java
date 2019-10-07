package com.github.akraskovski.oss.web.controllers;

import com.github.akraskovski.oss.services.OrderService;
import com.github.akraskovski.oss.web.requests.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RequestMapping("order")
@RestController
public class OrderCommandController {

    private final OrderService orderService;

    @PostMapping
    public CompletableFuture<String> create(@RequestBody @Valid CreateOrderRequest request) {
        return orderService.create(request);
    }

}
