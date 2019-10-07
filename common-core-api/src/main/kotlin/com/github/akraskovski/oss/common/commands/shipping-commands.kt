package com.github.akraskovski.oss.common.commands

import javax.validation.constraints.NotNull

data class ShipOrderCommand(
        val shippingId: String,
        @NotNull val invoiceId: String,
        @NotNull val orderId: String
) : BaseCommand<String>(shippingId)

data class FinishOrderShipCommand(val shippingId: String) : BaseCommand<String>(shippingId)