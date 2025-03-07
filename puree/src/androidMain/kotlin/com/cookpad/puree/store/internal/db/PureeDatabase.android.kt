package com.cookpad.puree.store.internal.db

import androidx.room.Room
import androidx.sqlite.driver.AndroidSQLiteDriver
import com.cookpad.puree.pureeApplicationContext
import kotlinx.coroutines.Dispatchers

internal actual fun getPureeDatabase(name: String): PureeDatabase {
    val dbFile = pureeApplicationContext.getDatabasePath(name)
    val databaseBuilder = Room.databaseBuilder<PureeDatabase>(
        context = pureeApplicationContext,
        name = dbFile.absolutePath,
    )

    return databaseBuilder
        .fallbackToDestructiveMigrationOnDowngrade(false)
        .setDriver(AndroidSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
