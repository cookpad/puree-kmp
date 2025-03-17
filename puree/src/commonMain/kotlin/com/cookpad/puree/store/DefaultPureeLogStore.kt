package com.cookpad.puree.store

import com.cookpad.puree.output.PureeBufferedLog
import com.cookpad.puree.store.internal.db.PureeLogEntity
import com.cookpad.puree.store.internal.db.getPureeDatabase
import com.cookpad.puree.type.toJsonObject
import kotlinx.datetime.Instant
import kotlin.time.Duration

/**
 * Stores the logs in a SQLite database.
 *
 * @param dbName The name of the database.
 */
class DefaultPureeLogStore(dbName: String) : PureeLogStore {

    private val database = getPureeDatabase(dbName)
    private val dao = database.pureeLogDao()

    override suspend fun add(outputId: String, bufferedLog: PureeBufferedLog) {
        dao.insert(
            PureeLogEntity(
                outputId = outputId,
                createdAt = bufferedLog.createdAt.toString(),
                log = bufferedLog.log.toString(),
            ),
        )
    }

    override suspend fun get(outputId: String, maxCount: Int): List<PureeBufferedLog> {
        return dao.select(outputId, maxCount).map {
            PureeBufferedLog(
                id = it.id,
                createdAt = Instant.parse(it.createdAt),
                log = it.log.toJsonObject(),
            )
        }
    }

    override suspend fun remove(outputId: String, bufferedLogs: List<PureeBufferedLog>) {
        dao.delete(bufferedLogs.map { PureeLogEntity(id = it.id) })
    }

    override suspend fun purgeLogsWithAge(outputId: String, now: Instant, age: Duration) {
        dao.deleteCreatedUpTo(now.minus(age).toString())
    }
}
