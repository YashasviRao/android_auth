package net.simplifiedcoding.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface TasksDao {
    @Query("SELECT * from tasks ORDER BY id ASC")
    fun getAllItems(): Flow<List<Tasks>>

    @Query("SELECT * from tasks WHERE id = :id")
    fun getItem(id: Int): Flow<Tasks>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.

    @Update
    suspend fun update(task: Tasks)

    @Delete
    suspend fun delete(task: Tasks)
}