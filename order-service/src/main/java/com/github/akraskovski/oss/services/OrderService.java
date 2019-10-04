package com.github.akraskovski.oss.services;

import com.github.akraskovski.oss.common.commands.CreateOrderCommand;
import com.github.akraskovski.oss.web.requests.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CommandGateway commandGateway;

    /**
     * Sends {@link CreateOrderCommand} to a command bus.
     *
     * @return created order id
     */
    public CompletableFuture<String> create(CreateOrderRequest request) {
        var command = new CreateOrderCommand(UUID.randomUUID().toString(), request.getType(), request.getPrice());
        return commandGateway.send(command);
    }

}
