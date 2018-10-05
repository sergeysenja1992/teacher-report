package ua.pp.ssenko.report

import com.vaadin.annotations.Theme
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.*
import org.vaadin.oauth.FacebookButton
import org.vaadin.oauth.OAuthButton


@Theme("valo")
@SpringUI(path = "")
class MainView: UI() {

    override fun init(p0: VaadinRequest?) {

        val mainLayout = VerticalLayout()

        with(mainLayout) {

            val button = Button("Click me") {
                e -> Notification.show("Test")
            }
            addComponent(button)

            val oauthListener = object : OAuthButton.OAuthListener {
                override fun userAuthenticated(user: OAuthButton.User) {
                    Notification.show("user");
                }

                override fun failed(reason: String) {
                    Notification.show(reason);
                }
            }

            // Facebook; you could ask for email w/ this
            val loginButton = FacebookButton("505481103259446",
                    "ac29caafbd44e6a25894bdf6be12fd9c", oauthListener)
            addComponent(loginButton)

        }

    }

}

