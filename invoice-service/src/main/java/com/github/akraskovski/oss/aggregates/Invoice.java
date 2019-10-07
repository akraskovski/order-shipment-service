package com.github.akraskovski.oss.aggregates;

import com.github.akraskovski.oss.common.enums.InvoiceStatus;
import lombok.Getter;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Getter
@Aggregate
public class Invoice {

    @AggregateIdentifier
    private String invoiceId;
    private String orderId;
    private InvoiceStatus invoiceStatus;

    public Invoice() {
    }
}
