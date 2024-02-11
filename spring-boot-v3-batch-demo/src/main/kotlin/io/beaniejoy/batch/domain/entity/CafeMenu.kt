package io.beaniejoy.batch.domain.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "cafe_menus")
class CafeMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    private val name: String? = null

    private val price: BigDecimal? = null

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private val cafe: Cafe? = null
}