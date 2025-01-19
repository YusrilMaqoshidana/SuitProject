package id.usereal.suitproject.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.usereal.suitproject.data.model.DataItem

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUser(users: List<DataItem>)

    @Query("SELECT * FROM users")
    fun getAllUser(): PagingSource<Int, DataItem>

    @Query("DELETE FROM users")
    suspend fun deleteAllUser()
}