package com.github.akraskovski.oss.common.commands

import java.math.BigDecimal

data class CreateOrderCommand(
        val orderId: String,
        val itemType: String,
        val price: BigDecimal
) : BaseCommand<String>(orderId)