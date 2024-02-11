package io.beaniejoy.batch.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "cafes")
class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    private val name: String? = null

    private val address: String? = null

    private val phoneNumber: String? = null

    private val description: String? = null

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    private val cafeMenus: List<CafeMenu>? = null
}