package com.tasbeeh.counter.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tasbeeh.counter.domain.model.SavedCount
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedCountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(savedCount: SavedCount)

    @Delete
    suspend fun delete(savedCount: SavedCount)

    @Query("SELECT * FROM saved_counts ORDER BY timestamp DESC")
    fun getAllByDateDesc(): Flow<List<SavedCount>>

    @Query("SELECT * FROM saved_counts ORDER BY timestamp ASC")
    fun getAllByDateAsc(): Flow<List<SavedCount>>

    @Query("SELECT * FROM saved_counts ORDER BY count DESC")
    fun getAllByCountDesc(): Flow<List<SavedCount>>

    @Query("SELECT * FROM saved_counts ORDER BY count ASC")
    fun getAllByCountAsc(): Flow<List<SavedCount>>

    @Query("DELETE FROM saved_counts")
    suspend fun deleteAll()
}
