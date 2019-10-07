package com.github.akraskovski.oss.common.commands

import com.github.akraskovski.oss.common.enums.OrderStatus
import java.math.BigDecimal

data class CreateOrderCommand(
        val orderId: String,
        val itemType: String,
        val price: BigDecimal
) : BaseCommand<String>(orderId)

data class UpdateOrderStatusCommand(
        val orderId: String,
        val orderStatus: OrderStatus
) : BaseCommand<String>(orderId)