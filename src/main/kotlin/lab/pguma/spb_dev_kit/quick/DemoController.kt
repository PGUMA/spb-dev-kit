package lab.pguma.spb_dev_kit.quick

import lab.pguma.spb_dev_kit.infra.notification.Notificator
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController(
    private val mail: Notificator,
    @Value("\${mail.test.to}")
    private val to: String,
    private val demoUseCase: DemoUseCase
) {

    @GetMapping("/hello")
    fun hello(): String = "Hello Spring Boot"

    @GetMapping("/notice")
    fun notice(): String {
        mail.notice(to)
        return "ok"
    }

    @GetMapping("/db")
    @ResponseBody
    fun dbAccess(): String {
        val result = demoUseCase.get()
        logger.info(result.toString())
        return result.toString()
    }

    companion object {
        val logger = LoggerFactory.getLogger(DemoController::class.java)
    }
}