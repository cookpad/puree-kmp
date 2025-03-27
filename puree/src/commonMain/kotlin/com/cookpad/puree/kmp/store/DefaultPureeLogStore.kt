package com.cookpad.puree.kmp.store

import com.cookpad.puree.kmp.output.PureeBufferedLog
import com.cookpad.puree.kmp.store.internal.db.PureeLogDao
import com.cookpad.puree.kmp.store.internal.db.PureeLogEntity
import com.cookpad.puree.kmp.type.toJsonObject
import kotlinx.datetime.Instant
import kotlin.time.Duration

/**
 * Stores the logs in a SQLite database.
 *
 * @param dbName The name of the database.
 */
internal class DefaultPureeLogStore(
    private val dao: PureeLogDao,
) : PureeLogStore {

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

expect class PlatformDefaultPureeLogStore : PureeLogStore
