package net.simplifiedcoding.data

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow


    /**
     * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
     */
    interface TasksRepository {
        /**
         * Retrieve all the items from the the given data source.
         */
        fun getAllItemsStream(): Flow<List<Tasks>>

        fun getItemStream(id: Int): Flow<Tasks?>

        /**
         * Delete item from the data source
         */
        suspend fun deleteItem(item: Tasks)

        /**
         * Update item in the data source
         */
        suspend fun updateItem(item: Tasks)
    }