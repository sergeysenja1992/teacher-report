package ua.pp.ssenko.report.ui.view

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl
import com.vaadin.navigator.View
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.*
import org.vaadin.oauth.OAuthButton
import ua.pp.ssenko.report.config.OAuthProperties
import ua.pp.ssenko.report.domain.Account
import ua.pp.ssenko.report.ui.MainUi
import java.util.*

@SpringView(name = "main")
class MainView(
        val oauthProperties: OAuthProperties
): VerticalLayout(), View {

    override fun getViewComponent(): Component {

        addComponent(Label("Main view"));

        setSizeFull()
        return this
    }

}
