package com.github.akraskovski.oss.common.events

data class InvoiceCreatedEvent(val invoiceId: String, val orderId: String)