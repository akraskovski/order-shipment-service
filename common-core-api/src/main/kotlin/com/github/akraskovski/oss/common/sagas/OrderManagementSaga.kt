package com.github.akraskovski.oss.common.sagas

import com.github.akraskovski.oss.common.commands.CreateInvoiceCommand
import com.github.akraskovski.oss.common.commands.ShipOrderCommand
import com.github.akraskovski.oss.common.commands.UpdateOrderStatusCommand
import com.github.akraskovski.oss.common.enums.OrderStatus
import com.github.akraskovski.oss.common.events.InvoiceCreatedEvent
import com.github.akraskovski.oss.common.events.OrderCreatedEvent
import com.github.akraskovski.oss.common.events.OrderShippedEvent
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.EndSaga
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@Saga
open class OrderManagementSaga {

    @Autowired
    @Transient
    private lateinit var commandGateway: CommandGateway

    @Transient
    private val log: Logger = LoggerFactory.getLogger(OrderManagementSaga::class.java)

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    fun on(event: OrderCreatedEvent) {
        log.info("Starting Saga with event: $event")
        val paymentId = UUID.randomUUID().toString()

        SagaLifecycle.associateWith("invoiceId", paymentId)

        commandGateway.send<Any>(CreateInvoiceCommand(paymentId, event.orderId))
        commandGateway.send<Any>(UpdateOrderStatusCommand(event.orderId, OrderStatus.PAID))
    }

    @SagaEventHandler(associationProperty = "invoiceId")
    fun on(event: InvoiceCreatedEvent) {
        log.info("Continue Saga with event: $event")
        val shippingId = UUID.randomUUID().toString()

        SagaLifecycle.associateWith("shippingId", shippingId)

        commandGateway.send<Any>(ShipOrderCommand(shippingId, event.invoiceId, event.orderId))
        commandGateway.send<Any>(UpdateOrderStatusCommand(event.orderId, OrderStatus.SHIPPING))
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "shippingId")
    fun on(event: OrderShippedEvent) {
        log.info("Finishing Saga with event: $event")

        commandGateway.send<Any>(UpdateOrderStatusCommand(event.orderId, OrderStatus.SHIPPED))
    }
}