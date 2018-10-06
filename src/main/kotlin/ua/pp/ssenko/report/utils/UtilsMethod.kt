package ua.pp.ssenko.report.utils

import com.vaadin.server.AbstractClientConnector
import com.vaadin.server.VaadinSession
import com.vaadin.ui.Component
import java.util.*

fun Any.setLang(lang: String) {
    VaadinSession.getCurrent().setAttribute(Tr::class.java, Tr(lang))
}

val Component.tr
        get() = VaadinSession.getCurrent().getAttribute(Tr::class.java)
