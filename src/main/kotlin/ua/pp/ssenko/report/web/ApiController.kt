package ua.pp.ssenko.report.web

import com.vaadin.ui.Notification
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ua.pp.ssenko.report.domain.User
import ua.pp.ssenko.report.exception.BadCredentialsException
import ua.pp.ssenko.report.repository.UserRepository
import ua.pp.ssenko.report.ui.MainView

@RestController
class ApiController(
        private val userRepository: UserRepository
) {

    data class LoginDto (val phoneNumber: String, val authCode: String?)

    @PostMapping("/login/{qrCodeId}")
    fun login(@PathVariable qrCodeId: String, @RequestBody loginDto: LoginDto) {
        val ui = MainView.uis.get(qrCodeId)
        if (ui != null) {
            val user = userRepository.findByNumber(loginDto.phoneNumber)
            if (user == null) {
                userRepository.save(User(loginDto.phoneNumber, qrCodeId))
                ui.access {
                    Notification.show("QRCODE")
                }
                ui.push()
                return
            }
            if (user.requestKey.equals(loginDto.authCode)) {
                ui.access {
                    Notification.show("AUTH")
                    // auth
                }
                ui.push()
            } else {
                throw BadCredentialsException();
            }

        }
    }

}
