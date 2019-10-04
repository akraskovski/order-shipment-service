package com.github.akraskovski.oss.common.events

import com.github.akraskovski.oss.common.enums.OrderStatus
import java.math.BigDecimal

data class OrderCreatedEvent(
        val orderId: String,
        val itemType: String,
        val price: BigDecimal,
        val status: OrderStatus
)