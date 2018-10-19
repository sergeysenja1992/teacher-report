package ua.pp.ssenko.report.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.SEQUENCE
import javax.persistence.Id
import javax.persistence.SequenceGenerator

@Entity
data class Account (
        @Id
        @GeneratedValue(strategy = SEQUENCE, generator = "sequenceGenerator")
        @SequenceGenerator(name = "sequenceGenerator")
        val id: Long = 0,

        val email: String
)


