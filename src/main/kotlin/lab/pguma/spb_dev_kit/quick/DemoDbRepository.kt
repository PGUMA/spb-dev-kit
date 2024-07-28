package lab.pguma.spb_dev_kit.quick

import lab.pguma.spb_dev_kit.infra.jooq.tables.DemoTable.DEMO_TABLE
import lab.pguma.spb_dev_kit.infra.jooq.tables.records.DemoTableRecord
import lab.pguma.spb_dev_kit.infra.repository.Demo
import lab.pguma.spb_dev_kit.infra.repository.DemoRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class DemoDbRepository(
    private val ctx: DSLContext
) : DemoRepository {
    override fun get(key: Int): Demo? {
        val result = ctx.selectFrom(DEMO_TABLE).where(DEMO_TABLE.INTEGER_COL.eq(key)).fetchOne()
        return result.toDomain()
    }

    private fun DemoTableRecord?.toDomain(): Demo? {
        return this?.let {
            Demo(integerCol, varcharCol, booleanCol)
        }
    }
}