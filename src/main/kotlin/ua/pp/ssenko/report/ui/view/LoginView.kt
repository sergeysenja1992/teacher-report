package ua.pp.ssenko.report.ui.view

import com.vaadin.navigator.View
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Component
import com.vaadin.ui.Notification
import com.vaadin.ui.VerticalLayout
import org.vaadin.addon.oauthpopup.OAuthListener
import org.vaadin.addon.oauthpopup.buttons.GooglePlusButton

@SpringView(name = "login")
class LoginView(
): VerticalLayout(), View {

    override fun getViewComponent(): Component {

        val ob = GooglePlusButton(TW_KEY, TW_SECRET)

        ob.addOAuthListener(OAuthListener());

        addComponent(ob);

        setSizeFull()
        return this
    }

}
