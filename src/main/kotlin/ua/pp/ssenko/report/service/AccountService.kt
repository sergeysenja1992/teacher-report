package ua.pp.ssenko.report.service

import org.springframework.stereotype.Service
import ua.pp.ssenko.report.domain.Account
import ua.pp.ssenko.report.repository.AccountRepository

@Service
class AccountService(
        val accountRepository: AccountRepository
) {

    fun loginUser(email: String, state: String): Account {
        val account = accountRepository.findByEmail(email)
        if (!state.startsWith("PHONE_")) {
            return account ?: accountRepository.save(Account(email = email))
        }
        if (account == null) {
            return accountRepository.save(Account(email = email, authCodes = mutableListOf(state)))
        }
        if (account.authCodes.contains(state)) {
            return account
        }
        account.authCodes.add(state)
        return accountRepository.save(account)
    }

}