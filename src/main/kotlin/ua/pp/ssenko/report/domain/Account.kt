package ua.pp.ssenko.report.domain

import javax.persistence.*

@Entity
data class Account (
        @Id
        @GeneratedValue
        val id: Long = 0,
        @Column(unique = true)
        var email: String
)

@Entity
data class AuthCode (
        @Id
        @GeneratedValue
        val id: Long = 0,
        @Column(unique = true)
        val authCode: String,
        @ManyToOne(optional = false)
        val account: Account
)


