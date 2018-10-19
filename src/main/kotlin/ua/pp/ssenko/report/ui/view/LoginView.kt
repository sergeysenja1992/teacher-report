package ua.pp.ssenko.report.ui.view

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl
import com.vaadin.navigator.View
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Alignment
import com.vaadin.ui.Button
import com.vaadin.ui.Component
import com.vaadin.ui.VerticalLayout
import ua.pp.ssenko.report.config.OAuthProperties
import ua.pp.ssenko.report.ui.MainUi
import ua.pp.ssenko.report.utils.tr
import java.util.*


@SpringView(name = "login")
class LoginView(
        val oauthProperties: OAuthProperties
): VerticalLayout(), View {

    override fun getViewComponent(): Component {

        val signInButton = Button(tr.`Sign in from google`)
        signInButton.addClickListener {
            val state = UUID.randomUUID().toString()
            MainUi.uis.put(state, ui)
            val authUrl = AuthorizationCodeRequestUrl(oauthProperties.userAuthorizationUri,
                    oauthProperties.clientId).setScopes(listOf(oauthProperties.scopes))
                    .setRedirectUri(oauthProperties.redirectUri).setState(state)
                    .build()
            ui.page.setLocation(authUrl)
        }

        addComponent(signInButton)

        setComponentAlignment(signInButton, Alignment.MIDDLE_CENTER)

        setSizeFull()
        return this
    }

}
