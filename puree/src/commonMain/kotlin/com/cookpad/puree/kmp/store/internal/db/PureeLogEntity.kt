package com.cookpad.puree.kmp.store.internal.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logs")
internal data class PureeLogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "output_id")
    val outputId: String = "",
    @ColumnInfo(name = "created_at")
    val createdAt: String = "",
    @ColumnInfo(name = "log")
    val log: String = "",
)
