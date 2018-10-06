package ua.pp.ssenko.report.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class User (
        @Id
        val number: String,
        val requestKey: String
)


