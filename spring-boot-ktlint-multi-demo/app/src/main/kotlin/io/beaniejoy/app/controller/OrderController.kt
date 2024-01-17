package io.beaniejoy.app.controller

import io.beaniejoy.domain.service.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val orderService: OrderService
) {
    @GetMapping("/{orderId}")
    fun getOrder(
        @PathVariable("orderId") orderId: Long
    ) {
        orderService.getOrder(orderId)
    }
}
