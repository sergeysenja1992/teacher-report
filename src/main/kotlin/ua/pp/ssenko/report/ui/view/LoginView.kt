package ua.pp.ssenko.report.ui.view

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl
import com.vaadin.navigator.View
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Button
import com.vaadin.ui.Component
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import org.vaadin.oauth.OAuthButton
import ua.pp.ssenko.report.config.OAuthProperties
import ua.pp.ssenko.report.domain.Account
import ua.pp.ssenko.report.ui.MainUi
import java.util.*
import jdk.nashorn.internal.objects.NativeJava.extend
import com.vaadin.server.BrowserWindowOpener
import com.vaadin.server.ExternalResource


@SpringView(name = "login")
class LoginView(
        val oauthProperties: OAuthProperties
): VerticalLayout(), View {

    override fun getViewComponent(): Component {

        val signInButton = Button("Sign in from google")
        signInButton.addClickListener {
            val state = UUID.randomUUID().toString()
            MainUi.uis.put(state, ui)
            val authUrl = AuthorizationCodeRequestUrl(oauthProperties.userAuthorizationUri,
                    oauthProperties.clientId).setScopes(listOf(oauthProperties.scopes))
                    .setRedirectUri(oauthProperties.redirectUri).setState(state)
                    .build()
            ui.page.setLocation(authUrl)
        }

        addComponent(signInButton);

        setSizeFull()
        return this
    }

}
