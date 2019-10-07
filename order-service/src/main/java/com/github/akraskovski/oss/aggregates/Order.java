package com.github.akraskovski.oss.aggregates;

import com.github.akraskovski.oss.common.commands.CreateOrderCommand;
import com.github.akraskovski.oss.common.commands.UpdateOrderStatusCommand;
import com.github.akraskovski.oss.common.enums.OrderStatus;
import com.github.akraskovski.oss.common.events.OrderCreatedEvent;
import com.github.akraskovski.oss.common.events.OrderStatusUpdatedEvent;
import lombok.Getter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Getter
public class Order {

    @AggregateIdentifier(routingKey = "id")
    private String orderId;
    private BigDecimal price;
    private String currency;
    private OrderStatus orderStatus;

    @CommandHandler
    public Order(CreateOrderCommand command) {
        apply(new OrderCreatedEvent(command.getOrderId(), command.getItemType(), command.getPrice(), OrderStatus.CREATED));
    }

    public void handle(UpdateOrderStatusCommand command) {
        apply(new OrderStatusUpdatedEvent(command.getOrderId(), command.getOrderStatus()));
    }

    @EventSourcingHandler
    protected void on(OrderCreatedEvent event) {
        orderId = event.getOrderId();
        price = event.getPrice();
        currency = "USD";
        orderStatus = event.getStatus();
    }

    @EventSourcingHandler
    protected void on(OrderStatusUpdatedEvent event) {
        this.orderStatus = event.getStatus();
    }
}
