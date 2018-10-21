package ua.pp.ssenko.report.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class VerificationController {

    @GetMapping("google8d0efe798c0987ec.html")
    @ResponseBody
    fun verification() = "google-site-verification: google8d0efe798c0987ec.html"

}