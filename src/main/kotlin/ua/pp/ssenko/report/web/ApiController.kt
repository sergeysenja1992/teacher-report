package ua.pp.ssenko.report.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ua.pp.ssenko.report.domain.Account
import ua.pp.ssenko.report.service.AccountService
import ua.pp.ssenko.report.service.GoogleService
import ua.pp.ssenko.report.ui.MainUi
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController("/api")
class ApiController(
        private val accountService: AccountService,
        private val googleService: GoogleService
) {

    @GetMapping("/oauth2callback")
    fun oauth(request: HttpServletRequest,
              @RequestParam("state") state: String,
              response: HttpServletResponse)  {

        val email = googleService.getAuthEmail(request.requestURL.toString(), request.queryString)
        val account = accountService.loginUser(email)

        val ui = MainUi.uis.get(state)
        ui?.session?.setAttribute(Account::class.java, account)
        response.sendRedirect("/#!main")

    }

    //@GetMapping("/acceptPhoneLogin")



}
