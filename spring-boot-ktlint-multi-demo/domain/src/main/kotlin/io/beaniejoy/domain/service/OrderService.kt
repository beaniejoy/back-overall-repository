package io.beaniejoy.domain.service

import io.beaniejoy.domain.port.OrderInfo
import io.beaniejoy.domain.port.OrderReaderPort
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderReaderPort: OrderReaderPort
) {
    fun getOrder(orderId: Long): OrderInfo.OrderDetail {
        val order = orderReaderPort.getOrder(orderId)

        return OrderInfo.OrderDetail(
            orderId = order.id,
            paymentId = order.payment?.id
        )
    }
}
