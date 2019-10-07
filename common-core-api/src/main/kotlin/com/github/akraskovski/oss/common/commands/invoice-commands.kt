package com.github.akraskovski.oss.common.commands

import javax.validation.constraints.NotNull

data class CreateInvoiceCommand(
        val invoiceId: String,
        @NotNull val orderId: String
) : BaseCommand<String>(invoiceId)