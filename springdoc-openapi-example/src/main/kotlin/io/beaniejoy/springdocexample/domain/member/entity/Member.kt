package io.beaniejoy.springdocexample.domain.member.entity

import javax.persistence.*

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    val role: Role,
)