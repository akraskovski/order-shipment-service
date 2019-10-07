package com.github.akraskovski.oss.common.commands

data class ShipOrderCommand(
        val shippingId: String,
        val invoiceId: String,
        val orderId: String
) : BaseCommand<String>(shippingId)