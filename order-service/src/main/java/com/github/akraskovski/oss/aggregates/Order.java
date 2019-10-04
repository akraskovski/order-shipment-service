package com.github.akraskovski.oss.aggregates;

import com.github.akraskovski.oss.common.enums.OrderStatus;
import lombok.Getter;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
@Getter
public class Order {

    @AggregateIdentifier(routingKey = "id")
    private String orderId;
    private BigDecimal price;
    private String currency;
    private OrderStatus orderStatus;

    //todo add command and event-sourcing handlers
}
