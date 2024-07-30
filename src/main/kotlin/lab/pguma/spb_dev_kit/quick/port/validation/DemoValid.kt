package lab.pguma.spb_dev_kit.quick.port.validation

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [DemoValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class DemoValid(
    val requestParameterName: String,
    val message: String = "error",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Any>> = []
)

class DemoValidator: ConstraintValidator<DemoValid, String> {

    private var requestParameterName: String = ""

    override fun initialize(constraintAnnotation: DemoValid) {
        super.initialize(constraintAnnotation)
        this.requestParameterName = constraintAnnotation.requestParameterName
    }

    override fun isValid(p0: String?, p1: ConstraintValidatorContext?): Boolean {
        println("${this.toString()}/${this.requestParameterName}")
        return true
    }
}