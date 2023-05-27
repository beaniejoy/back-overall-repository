package io.beaniejoy.entity

import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(
    email: String,
    password: String,
    address: String?,
    phoneNumber: String?
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    val id: Long = 0L

    @Column(name = "email", nullable = false)
    var email: String = email
        protected set

    @Column(name = "password", nullable = false)
    var password: String = password
        protected set

    @Column(name = "address")
    var address: String? = address
        protected set

    @Column(name = "phone_number")
    var phoneNumber: String? = phoneNumber
        protected set

    companion object {
        fun createEntity(
            email: String,
            password: String,
            address: String?,
            phoneNumber: String?
        ): Member {
            return Member(
                email = email,
                password = password,
                address = address,
                phoneNumber = phoneNumber
            )
        }
    }
}