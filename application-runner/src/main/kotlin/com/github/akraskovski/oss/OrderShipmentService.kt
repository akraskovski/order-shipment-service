package com.github.akraskovski.oss

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class OrderShipmentService

fun main(args: Array<String>) {
    runApplication<OrderShipmentService>(*args)
}
