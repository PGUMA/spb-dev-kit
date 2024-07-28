package lab.pguma.spb_dev_kit.quick

import lab.pguma.spb_dev_kit.infra.repository.Demo
import lab.pguma.spb_dev_kit.infra.repository.DemoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DemoUseCase(
    private val demoRepository: DemoRepository
) {

    @Transactional
    fun get(): Demo {
        return demoRepository.get(1) ?: throw RuntimeException()
    }
}