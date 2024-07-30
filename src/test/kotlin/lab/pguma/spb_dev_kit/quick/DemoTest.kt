package lab.pguma.spb_dev_kit.quick

import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import lab.pguma.spb_dev_kit.infra.notification.Notificator
import lab.pguma.spb_dev_kit.infra.repository.Demo
import lab.pguma.spb_dev_kit.infra.repository.DemoRepository
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test

class DemoTest {
    @Test
    fun demo() {

        class EmptyNotificator : Notificator {
            override fun notice(to: String) {
                TODO("Not yet implemented")
            }
        }

        class EmptyRepository : DemoRepository {
            override fun get(key: Int): Demo? {
                TODO("Not yet implemented")
            }
        }

        given()
            .standaloneSetup(DemoController(EmptyNotificator(), "", DemoUseCase(EmptyRepository())))
            .`when`().get("/get")
            .then().statusCode(200).body("value", equalTo("get"))
    }
}