package ua.pp.ssenko.report.ui.view

import com.vaadin.navigator.View
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.*
import com.vaadin.ui.Alignment.MIDDLE_CENTER
import com.vaadin.ui.Alignment.TOP_CENTER
import com.vaadin.ui.themes.ValoTheme
import fi.jasoft.qrcode.QRCode
import ua.pp.ssenko.report.ui.MainView
import ua.pp.ssenko.report.utils.tr
import java.util.*

@SpringView(name = "login")
class LoginView(
): VerticalLayout(), View {

    override fun getViewComponent(): Component {

        val label = Label(tr.`Scan qr code for login`)
        addComponent(label)
        setComponentAlignment(label, TOP_CENTER)
        setExpandRatio(label, 0f)
        label.styleName = ValoTheme.LABEL_H2
        label.setSizeUndefined()

        val id = UUID.randomUUID().toString()

        val qrCode = QRCode()
        qrCode.value = id
        qrCode.setHeight("100%");
        qrCode.setWidth("100%");
        qrCode.addDetachListener {
            MainView.uis.remove(id)
        }

        MainView.uis[id] = UI.getCurrent()

        addComponent(qrCode)
        setComponentAlignment(qrCode, MIDDLE_CENTER)
        setExpandRatio(qrCode, 1f)

        setSizeFull()
        return this
    }

}
