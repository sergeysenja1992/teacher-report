package ua.pp.ssenko.report.repository

import org.springframework.data.repository.CrudRepository
import ua.pp.ssenko.report.domain.Account
import ua.pp.ssenko.report.domain.AuthCode

interface AccountRepository: CrudRepository<Account, Long> {

    fun findByEmail(email: String): Account?

}

interface AuthCodeRepository: CrudRepository<AuthCode, Long> {

    fun findByAuthCode(authCode: String): AuthCode?

}
