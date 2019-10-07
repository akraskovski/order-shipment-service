package com.github.akraskovski.oss.common.sagas

import com.github.akraskovski.oss.common.commands.CreateInvoiceCommand
import com.github.akraskovski.oss.common.commands.ShipOrderCommand
import com.github.akraskovski.oss.common.commands.UpdateOrderStatusCommand
import com.github.akraskovski.oss.common.enums.OrderStatus
import com.github.akraskovski.oss.common.events.InvoiceCreatedEvent
import com.github.akraskovski.oss.common.events.OrderCreatedEvent
import com.github.akraskovski.oss.common.events.OrderShippedEvent
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import java.util.*

@Saga
open class OrderManagementSaga {

    @Transient
    private lateinit var commandGateway: CommandGateway

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    fun on(event: OrderCreatedEvent) {
        println("Starting Saga with event: $event")
        val paymentId = UUID.randomUUID().toString()

        SagaLifecycle.associateWith("invoiceId", paymentId)

        commandGateway.send<Any>(CreateInvoiceCommand(paymentId, event.orderId))
        commandGateway.send<Any>(UpdateOrderStatusCommand(event.orderId, OrderStatus.PAID))
    }

    @SagaEventHandler(associationProperty = "invoiceId")
    fun on(event: InvoiceCreatedEvent) {
        println("Continue Saga with event: $event")
        val shippingId = UUID.randomUUID().toString()

        SagaLifecycle.associateWith("shippingId", shippingId)

        commandGateway.send<Any>(ShipOrderCommand(shippingId, event.invoiceId, event.orderId))
        commandGateway.send<Any>(UpdateOrderStatusCommand(event.orderId, OrderStatus.SHIPPING))
    }

    @SagaEventHandler(associationProperty = "shippingId")
    fun on(event: OrderShippedEvent) {
        println("Finishing Saga with event: $event")

        commandGateway.send<Any>(UpdateOrderStatusCommand(event.orderId, OrderStatus.SHIPPED))
    }
}