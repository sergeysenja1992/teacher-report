package ua.pp.ssenko.report.utils

import com.vaadin.server.VaadinSession
import com.vaadin.ui.Component
import org.slf4j.LoggerFactory

fun Any.setLang(lang: String) {
    VaadinSession.getCurrent().setAttribute(Tr::class.java, Tr(lang))
}

fun Any.log() = LoggerFactory.getLogger(this::class.java);

val Component.tr
        get() = VaadinSession.getCurrent().getAttribute(Tr::class.java)
