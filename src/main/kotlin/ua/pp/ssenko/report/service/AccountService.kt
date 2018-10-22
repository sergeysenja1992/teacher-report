package ua.pp.ssenko.report.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ua.pp.ssenko.report.domain.Account
import ua.pp.ssenko.report.domain.AuthCode
import ua.pp.ssenko.report.repository.AccountRepository
import ua.pp.ssenko.report.repository.AuthCodeRepository
import ua.pp.ssenko.report.utils.PHONE_PREFIX

@Service
@Transactional
class AccountService(
        val accountRepository: AccountRepository,
        val authCodeRepository: AuthCodeRepository
) {

    fun loginUser(email: String): Account {
        val account = accountRepository.findByEmail(email)
        return account ?: accountRepository.save(Account(email = email))
    }

    fun loginPhone(token: Token, authCode: String) {
        val account = loginUser(token.email)
        authCodeRepository.save(AuthCode(authCode = authCode, account = account))
    }

}

data class Token(val email: String, val name: String)
