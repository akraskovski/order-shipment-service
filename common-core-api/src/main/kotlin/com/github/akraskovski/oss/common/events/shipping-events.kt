package com.github.akraskovski.oss.common.events

data class StartOrderShippingEvent(val shippingId: String, val invoiceId: String, val orderId: String)
data class OrderShippedEvent(val shippingId: String, val invoiceId: String, val orderId: String)