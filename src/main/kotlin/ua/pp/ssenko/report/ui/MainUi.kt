package ua.pp.ssenko.report.ui

import com.vaadin.annotations.Push
import com.vaadin.annotations.Theme
import com.vaadin.navigator.Navigator
import com.vaadin.server.Page
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.spring.navigator.SpringViewProvider
import com.vaadin.ui.UI
import ua.pp.ssenko.report.utils.setLang
import java.util.concurrent.ConcurrentHashMap


@Theme("valo")
@SpringUI(path = "")
@Push
class MainUi(
        private val viewProvider: SpringViewProvider
) : UI() {

    companion object {
        val uis = ConcurrentHashMap<String, UI>()
    }

    override fun init(request: VaadinRequest?) {

        setLang("ru")

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
            navigator.navigateTo(route)
        }
    }


}

