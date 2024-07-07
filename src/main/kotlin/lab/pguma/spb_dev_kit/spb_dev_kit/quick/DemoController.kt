package lab.pguma.spb_dev_kit.spb_dev_kit.quick

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController {

    @GetMapping("/hello")
    fun hello(): String = "Hello Spring Boot"
}