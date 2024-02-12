package io.beaniejoy.batch.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "cafes")
class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "name")
    val name: String? = null

    @Column(name = "address")
    val address: String? = null

    @Column(name = "phone_number")
    val phoneNumber: String? = null

    @Column(name = "description")
    val description: String? = null

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val cafeMenus: MutableList<CafeMenu> = arrayListOf()
}