package io.beaniejoy.springdocexample.domain.cafe.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Cafe(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val name: String,

    val address: String,

    @OneToMany(mappedBy = "cafe")
    val menuItems: MutableList<MenuItem> = mutableListOf(),

    @Column(nullable = true)
    val phoneNumber: String?
)