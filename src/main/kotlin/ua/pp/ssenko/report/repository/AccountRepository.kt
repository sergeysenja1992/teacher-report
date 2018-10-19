package ua.pp.ssenko.report.repository

import org.springframework.data.repository.CrudRepository
import ua.pp.ssenko.report.domain.Account

interface AccountRepository: CrudRepository<Account, String> {

    fun findByEmail(email: String): Account?

}
