package lab.pguma.spb_dev_kit.spb_dev_kit.infra.notification

import org.springframework.stereotype.Component

interface Notificator {
    fun notice(to: String)
}

@Component
class Mail(
    private val resendClient: MailClient
) : Notificator {
    override fun notice(to: String) {
        resendClient.send(
            MailRequest(
                from = "onboarding@resend.dev",
                to = to,
                subject = "Demo Mail",
                body = "<strong>hello mail</strong>",
            )
        )
    }
}