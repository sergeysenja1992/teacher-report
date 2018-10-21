package ua.pp.ssenko.report.service

import org.springframework.stereotype.Service
import ua.pp.ssenko.report.domain.Account
import ua.pp.ssenko.report.domain.AuthCode
import ua.pp.ssenko.report.repository.AccountRepository
import ua.pp.ssenko.report.repository.AuthCodeRepository
import ua.pp.ssenko.report.utils.PHONE_PREFIX

@Service
class AccountService(
        val accountRepository: AccountRepository,
        val authCodeRepository: AuthCodeRepository
) {

    fun loginUser(email: String): Account {
        var account = accountRepository.findByEmail(email)
        return account ?: accountRepository.save(Account(email = email))
    }

}