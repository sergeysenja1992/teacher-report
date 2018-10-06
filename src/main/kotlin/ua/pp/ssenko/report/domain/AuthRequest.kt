package ua.pp.ssenko.report.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class AuthRequest (
        @Id
        val requestKey: String,
        val number: String? = null,
        val timestamp: Long = System.currentTimeMillis()
)
