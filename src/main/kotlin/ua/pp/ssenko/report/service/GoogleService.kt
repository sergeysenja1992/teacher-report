package ua.pp.ssenko.report.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest
import com.google.api.client.auth.oauth2.ClientParametersAuthentication
import com.google.api.client.http.GenericUrl
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.apache.commons.io.IOUtils
import org.springframework.stereotype.Service
import ua.pp.ssenko.report.config.OAuthProperties
import ua.pp.ssenko.report.utils.log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.security.PublicKey
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.*


data class UserDto(val email: String)

@Service
class GoogleService (
        private val oauthProperties: OAuthProperties,
        private val objectMapper: ObjectMapper
) {
    private val log = log()

    init {

    }

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

    fun verifyIdToken(idToken: String): Boolean {
        val certificates = objectMapper.readValue<Map<String, String>>(fetchCertificate())

        log.info("Token verification {}", idToken)
        val token = Base64.getDecoder().decode(idToken.split(".")[0])
        val kid = objectMapper.readValue<Map<String, String>>(token).get("kid")
        val certificate = certificates.get(kid)!!

        try {

            Jwts.parser()
                    .setSigningKey(getPublic(certificate))
                    .parseClaimsJws(idToken);

            log.info("Success token verification")
            return true
        } catch (e: JwtException) {
            log.error("Error token verification", e)
            return false
        }
    }

    private fun fetchCertificate(): String {
        val url = "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com"
        with(URL(url).openConnection() as HttpURLConnection) {
            requestMethod = "GET"
            BufferedReader(InputStreamReader(inputStream)).use {
                return it.readText()
            }
        }
    }

    private fun getPublic(certificate: String): PublicKey {
        val fact = CertificateFactory.getInstance("X.509")
        val cer = fact.generateCertificate(IOUtils.toInputStream(certificate, "UTF-8")) as X509Certificate
        return cer.publicKey
    }

}
