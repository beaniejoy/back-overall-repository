package io.beaniejoy.springdocexample.domain.cafe.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity
class MenuItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val name: String,

    val price: BigDecimal,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    val cafe: Cafe
)