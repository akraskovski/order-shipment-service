package com.github.akraskovski.oss.common.commands

data class CreateInvoiceCommand(
        val invoiceId: String,
        val orderId: String
) : BaseCommand<String>(invoiceId)