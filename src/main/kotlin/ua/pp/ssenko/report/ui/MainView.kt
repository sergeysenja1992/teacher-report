package ua.pp.ssenko.report.ui

import com.vaadin.annotations.Theme
import com.vaadin.navigator.Navigator
import com.vaadin.server.Page
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.spring.navigator.SpringViewProvider
import com.vaadin.ui.*
import ua.pp.ssenko.report.ui.view.LoginView




@Theme("valo")
@SpringUI(path = "")
class MainView(
        private val viewProvider: SpringViewProvider
) : UI() {

    override fun init(request: VaadinRequest?) {
        navigator = Navigator(this, this)
        navigator.addProvider(viewProvider)

        //navigator.setErrorView(LoginPage::class.java)

        Page.getCurrent().addPopStateListener{
            event -> router(event.uri)
        }

        router("");
    }

    private fun router(route: String) {
        val user = session.getAttribute("user")
        if (user == null) {
            navigator.navigateTo("login")
        } else {

        }
    }


}

