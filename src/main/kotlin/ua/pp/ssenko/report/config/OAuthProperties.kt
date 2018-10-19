package ua.pp.ssenko.report.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.util.*


@Component
@ConfigurationProperties(prefix = "oauth2", ignoreUnknownFields = true)
class OAuthProperties {

    var clientId: String? = null
    var clientSecret: String? = null
    var userAuthorizationUri: String? = null
    var scopes: String? = null
    var redirectUri: String? = null
    var redirectPath: String? = null
    var accessTokenUri: String? = null

}

