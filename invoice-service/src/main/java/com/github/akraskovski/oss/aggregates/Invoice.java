package com.github.akraskovski.oss.aggregates;

import com.github.akraskovski.oss.common.commands.CreateInvoiceCommand;
import com.github.akraskovski.oss.common.enums.InvoiceStatus;
import com.github.akraskovski.oss.common.events.InvoiceCreatedEvent;
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
public class Invoice {

    @AggregateIdentifier
    private String invoiceId;
    private String orderId;
    private InvoiceStatus invoiceStatus;

    @CommandHandler
    public Invoice(CreateInvoiceCommand command) {
        log.info("Calling InvoiceCreatedEvent: {}", command);
        apply(new InvoiceCreatedEvent(command.getInvoiceId(), command.getOrderId()));
    }

    //todo revert payment command

    @EventSourcingHandler
    protected void on(InvoiceCreatedEvent event) {
        log.info("Invoice was created: {}", event);
        invoiceId = event.getInvoiceId();
        orderId = event.getOrderId();
        invoiceStatus = InvoiceStatus.PAID;
    }
}

