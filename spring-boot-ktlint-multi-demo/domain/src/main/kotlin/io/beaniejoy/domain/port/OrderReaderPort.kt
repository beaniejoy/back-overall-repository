package io.beaniejoy.domain.port

import io.beaniejoy.domain.entity.Order

interface OrderReaderPort {
    fun getOrder(orderId: Long): Order
}