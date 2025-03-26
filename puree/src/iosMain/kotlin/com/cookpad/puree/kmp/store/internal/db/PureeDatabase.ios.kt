package com.cookpad.puree.kmp.store.internal.db

import androidx.room.Room
import androidx.sqlite.driver.NativeSQLiteDriver
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
internal actual fun getPureeDatabase(name: String): PureeDatabase {
    val documentDir = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    val dbFilePath = "${documentDir?.path}/$name"

    return Room.databaseBuilder<PureeDatabase>(dbFilePath)
        .fallbackToDestructiveMigrationOnDowngrade(false)
        .setDriver(NativeSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
