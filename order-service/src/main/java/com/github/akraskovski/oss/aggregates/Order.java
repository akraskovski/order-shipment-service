package com.github.akraskovski.oss.aggregates;

import com.github.akraskovski.oss.common.commands.CreateOrderCommand;
import com.github.akraskovski.oss.common.commands.UpdateOrderStatusCommand;
import com.github.akraskovski.oss.common.enums.OrderStatus;
import com.github.akraskovski.oss.common.events.OrderCreatedEvent;
import com.github.akraskovski.oss.common.events.OrderStatusUpdatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
@Getter
@NoArgsConstructor
public class Order {

    @AggregateIdentifier(routingKey = "id")
    private String orderId;
    private BigDecimal price;
    private Currency currency;
    private OrderStatus orderStatus;

    @CommandHandler
    public Order(CreateOrderCommand command) {
        log.info("Calling OrderCreatedEvent: {}", command);
        apply(new OrderCreatedEvent(command.getOrderId(), command.getItemType(), command.getPrice(), OrderStatus.CREATED));
    }

    @CommandHandler
    public void handle(UpdateOrderStatusCommand command) {
        log.info("Calling OrderStatusUpdatedEvent: {}", command);
        apply(new OrderStatusUpdatedEvent(command.getOrderId(), command.getOrderStatus()));
    }

    @EventSourcingHandler
    protected void on(OrderCreatedEvent event) {
        log.info("OrderCreatedEvent: {}", event);
        orderId = event.getOrderId();
        price = event.getPrice();
        currency = Currency.getInstance(Locale.getDefault());
        orderStatus = event.getStatus();
    }

    @EventSourcingHandler
    protected void on(OrderStatusUpdatedEvent event) {
        log.info("OrderStatusUpdatedEvent: {}", event);
        this.orderStatus = event.getStatus();
    }
}
