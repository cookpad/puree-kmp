package com.cookpad.puree.kmp.store.internal.db

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.AndroidSQLiteDriver
import kotlinx.coroutines.Dispatchers

internal fun getPureeDatabase(context: Context, name: String): PureeDatabase {
    val dbFile = context.getDatabasePath(name)
    val databaseBuilder = Room.databaseBuilder<PureeDatabase>(
        context = context,
        name = dbFile.absolutePath,
    )

    return databaseBuilder
        .fallbackToDestructiveMigrationOnDowngrade(false)
        .setDriver(AndroidSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
