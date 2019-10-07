package com.github.akraskovski.oss.common.commands

import com.github.akraskovski.oss.common.enums.OrderStatus
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero

data class CreateOrderCommand(
        val orderId: String,
        @NotBlank val itemType: String,
        @PositiveOrZero val price: BigDecimal
) : BaseCommand<String>(orderId)

data class UpdateOrderStatusCommand(
        val orderId: String,
        @NotNull val orderStatus: OrderStatus
) : BaseCommand<String>(orderId)