package net.simplifiedcoding.data

import androidx.compose.runtime.getValue
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Entity(tableName = "tasks")
data class Tasks (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val label: String,
    var checked: Boolean = false
)
