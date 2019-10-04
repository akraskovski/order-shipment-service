package com.github.akraskovski.common.core.api.commands

import java.math.BigDecimal

data class CreateOrderCommand(
        val orderId: String,
        val itemType: String,
        val price: BigDecimal
) : BaseCommand<String>(orderId)