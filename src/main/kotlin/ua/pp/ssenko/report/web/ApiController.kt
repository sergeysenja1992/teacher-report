package ua.pp.ssenko.report.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest
import com.google.api.client.auth.oauth2.ClientParametersAuthentication
import com.google.api.client.http.GenericUrl
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import org.springframework.web.bind.annotation.*
import ua.pp.ssenko.report.config.OAuthProperties
import ua.pp.ssenko.report.domain.Account
import ua.pp.ssenko.report.repository.AccountRepository
import ua.pp.ssenko.report.ui.MainUi
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class ApiController(
        private val accountRepository: AccountRepository,
        private val oauthProperties: OAuthProperties,
        private val objectMapper: ObjectMapper
) {

    @GetMapping("/oauth2callback")
    fun oauth(request: HttpServletRequest,
              @RequestParam("state") state: String,
              response: HttpServletResponse)  {
        val email = getAuthEmail(request)
        val account = accountRepository.findByEmail(email)
        if (account == null) {
            accountRepository.save(Account(email = email))
        }
        val ui = MainUi.uis.get(state)
        //ui?.access {
        ui?.session?.setAttribute(Account::class.java, account)
        //}

        response.sendRedirect("/#!main")
    }


    private fun getAuthEmail(request: HttpServletRequest): String {
        val fullUrlBuf = request.requestURL
        if (request.queryString != null) {
            fullUrlBuf.append('?').append(request.queryString)
        }
        val authResponse = AuthorizationCodeResponseUrl(fullUrlBuf.toString())
        val response = AuthorizationCodeTokenRequest(NetHttpTransport(), JacksonFactory(),
                GenericUrl(oauthProperties.accessTokenUri), authResponse.code)
                .setClientAuthentication(ClientParametersAuthentication(oauthProperties.clientId,
                        oauthProperties.clientSecret))
                .setRedirectUri(oauthProperties.redirectUri).execute()
        val idToken = response.getValue("id_token")
        val userJson = Base64.getDecoder().decode(idToken.toString().split(".")[1])

        val userDto = objectMapper.readValue<UserDto>(userJson)
        return userDto.email
    }

}

data class UserDto(val email: String)
