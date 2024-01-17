package io.beaniejoy.persistence.adapter

import io.beaniejoy.domain.entity.Order
import io.beaniejoy.domain.port.OrderReaderPort
import io.beaniejoy.persistence.repository.OrderRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.lang.RuntimeException

@Component
class OrderReaderAdapter(
    private val orderRepository: OrderRepository
): OrderReaderPort {
    override fun getOrder(orderId: Long): Order {
        return orderRepository.findByIdOrNull(orderId)
            ?: throw RuntimeException("order not found")
    }
}