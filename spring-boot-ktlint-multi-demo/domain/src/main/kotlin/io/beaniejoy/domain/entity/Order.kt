package io.beaniejoy.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "orders")
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    val id: Long = 0L

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val payment: Payment? = null
}
