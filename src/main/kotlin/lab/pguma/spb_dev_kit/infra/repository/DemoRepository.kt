package lab.pguma.spb_dev_kit.infra.repository

interface DemoRepository {
    fun get(key: Int): Demo?
}

class Demo(
    private val intValue: Int,
    private val stringValue: String,
    private val booleanValue: Boolean
) {
    override fun toString(): String {
        return "${intValue}|${stringValue}|${booleanValue}"
    }
}