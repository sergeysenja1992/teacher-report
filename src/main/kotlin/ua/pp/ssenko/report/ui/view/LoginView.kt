package ua.pp.ssenko.report.ui.view

import com.vaadin.navigator.View
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.*
import com.vaadin.ui.Alignment.MIDDLE_CENTER
import fi.jasoft.qrcode.QRCode
import ua.pp.ssenko.report.domain.AuthRequest
import ua.pp.ssenko.report.repository.AuthRequestRepository
import java.util.*

@SpringView(name = "login")
class LoginView(
        val authRequestRepository: AuthRequestRepository
): VerticalLayout(), View {

    override fun getViewComponent(): Component {
        val id = UUID.randomUUID().toString()

        authRequestRepository.save(AuthRequest(requestKey = id))

        val qrCode = QRCode()
        qrCode.value = id
        addComponent(qrCode)
        setComponentAlignment(qrCode, MIDDLE_CENTER)

        return this
    }

}