package lab.pguma.spb_dev_kit.quick

import lab.pguma.spb_dev_kit.infra.notification.Notificator
import lab.pguma.spb_dev_kit.infra.repository.Demo
import lab.pguma.spb_dev_kit.quick.port.validation.DemoValid
import org.springframework.beans.factory.annotation.Value
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
class DemoController(
    private val mail: Notificator,
    @Value("\${mail.test.to}")
    private val to: String,
    private val demoUseCase: DemoUseCase
) {
    @GetMapping("/get")
    fun getMethod(): GetBody = GetBody("get")

    @PostMapping("/post")
    fun postMethod(@Validated @RequestBody payload: Payload): String {
        return "Post OK"
    }

    @GetMapping("/hello")
    fun hello(): String = "Hello Spring Boot"

    @GetMapping("/notice")
    fun notice(): String {
        mail.notice(to)
        return "ok"
    }

    @GetMapping("/db")
    @ResponseBody
    fun dbAccess(): Demo {
        return demoUseCase.get()
    }

}

data class GetBody(
    val value: String
)

data class Payload(
    @DemoValid("field1")
    val field1: String,

    @DemoValid("field2")
    val field2: String
)