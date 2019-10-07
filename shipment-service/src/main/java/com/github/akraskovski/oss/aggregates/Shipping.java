package com.github.akraskovski.oss.aggregates;

import com.github.akraskovski.oss.common.commands.ShipOrderCommand;
import com.github.akraskovski.oss.common.events.OrderShippedEvent;
import com.github.akraskovski.oss.common.events.StartOrderShippingEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
@Getter
@NoArgsConstructor
public class Shipping {

    @AggregateIdentifier
    private String shipmentId;
    private String invoiceId;
    private String orderId;

    @CommandHandler
    public Shipping(ShipOrderCommand command) {
        log.info("Calling StartOrderShippingEvent: {}", command);
        apply(new StartOrderShippingEvent(command.getShippingId(), command.getInvoiceId(), command.getOrderId()));
    }

    @EventSourcingHandler
    protected void on(StartOrderShippingEvent event) throws InterruptedException {
        shipmentId = event.getShippingId();

        log.info("Start shipping order: {}", event);
        Thread.sleep(1000);

        apply(new OrderShippedEvent(event.getShippingId(), event.getInvoiceId(), event.getOrderId()));
    }
}
