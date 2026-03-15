package com.tasbeeh.counter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_counts")
data class SavedCount(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val count: Int,
    val timestamp: Long = System.currentTimeMillis()
)
