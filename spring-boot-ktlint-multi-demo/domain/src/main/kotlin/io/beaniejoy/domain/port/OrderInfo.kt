package io.beaniejoy.domain.port

class OrderInfo {
    data class OrderDetail(
        val orderId: Long,
        val paymentId: Long?
    )
}