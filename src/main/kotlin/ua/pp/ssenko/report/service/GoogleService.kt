package ua.pp.ssenko.report.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest
import com.google.api.client.auth.oauth2.ClientParametersAuthentication
import com.google.api.client.http.GenericUrl
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import org.springframework.stereotype.Service
import ua.pp.ssenko.report.config.OAuthProperties
import java.util.*

data class UserDto(val email: String)

@Service
class GoogleService (
        private val oauthProperties: OAuthProperties,
        private val objectMapper: ObjectMapper
) {

    fun getAuthEmail(requestUrl: String, queryString: String): String {
        val fullUrlBuf = "$requestUrl?$queryString"

        val authResponse = AuthorizationCodeResponseUrl(fullUrlBuf)
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
