package lab.pguma.spb_dev_kit.spb_dev_kit.quick

import lab.pguma.spb_dev_kit.spb_dev_kit.infra.notification.Notificator
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController(
    private val mail: Notificator,
    @Value("\${mail.test.to}")
    private val to: String
) {

    @GetMapping("/hello")
    fun hello(): String = "Hello Spring Boot"

    @GetMapping("/notice")
    fun notice(): String {
        mail.notice(to)
        return "ok"
    }
}