package com.github.akraskovski.common.core.api.events

import com.github.akraskovski.common.core.api.enums.OrderStatus
import java.math.BigDecimal

data class OrderCreatedEvent(
        val orderId: String,
        val itemType: String,
        val price: BigDecimal,
        val status: OrderStatus
)