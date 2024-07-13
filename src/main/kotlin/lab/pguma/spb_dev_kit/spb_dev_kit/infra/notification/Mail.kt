package lab.pguma.spb_dev_kit.spb_dev_kit.infra.notification

import com.resend.Resend
import com.resend.core.exception.ResendException
import com.resend.services.emails.model.CreateEmailOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


interface MailClient {
    fun send(request: MailRequest)
}

data class MailRequest(
    val from: String,
    val to: String,
    val subject: String,
    val body: String,
    val isHtmlFormat: Boolean = true
)

@Component
class ResendClient(
    @Value("\${resend.api.key}")
    private val apiKey: String
): MailClient {

    private val client: Resend

    init {
        client = Resend(apiKey)
    }

    override fun send(request: MailRequest) {
        require(request.isHtmlFormat) { "resend only support html format" }

        val params = CreateEmailOptions.builder()
            .from(request.from)
            .to(request.to)
            .subject(request.subject)
            .html(request.body)
            .build()

        try {
            val data = client.emails().send(params)
        } catch (e: ResendException) {
            e.printStackTrace()
        }
    }
}