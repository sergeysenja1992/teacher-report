package ua.pp.ssenko.report.domain

import javax.persistence.*

@Entity
data class Account (
        @Id
        @GeneratedValue
        val id: Long = 0,
        @Column(unique = true)
        val email: String,
        @CollectionTable
        val authCodes: MutableList<String> = mutableListOf<String>()
)


