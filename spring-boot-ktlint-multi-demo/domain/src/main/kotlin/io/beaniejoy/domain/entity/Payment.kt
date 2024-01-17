package io.beaniejoy.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "payments")
class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    val id: Long = 0L

    @Column(name = "transaction_id")
    val transactionId: String? = null
}