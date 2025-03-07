package com.cookpad.puree.store.internal.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface PureeLogDao {
    @Query("SELECT * FROM logs WHERE output_id = :outputId LIMIT :count")
    fun select(outputId: String, count: Int): List<PureeLogEntity>

    @Insert
    fun insert(log: PureeLogEntity)

    @Delete
    fun delete(logs: List<PureeLogEntity>)

    @Query("DELETE FROM logs WHERE datetime(created_at) <= datetime(:createdUpTo)")
    fun deleteCreatedUpTo(createdUpTo: String)
}
