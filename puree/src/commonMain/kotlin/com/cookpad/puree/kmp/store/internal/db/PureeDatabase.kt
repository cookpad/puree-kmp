package com.cookpad.puree.kmp.store.internal.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

@Database(entities = [PureeLogEntity::class], version = 1)
@ConstructedBy(CookieDatabaseConstructor::class)
internal abstract class PureeDatabase : RoomDatabase() {
    abstract fun pureeLogDao(): PureeLogDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
internal expect object CookieDatabaseConstructor : RoomDatabaseConstructor<PureeDatabase> {
    override fun initialize(): PureeDatabase
}
