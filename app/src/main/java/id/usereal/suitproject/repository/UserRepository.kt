package id.usereal.suitproject.repository

import androidx.compose.runtime.currentRecomposeScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import id.usereal.suitproject.data.local.remote_mediator.RemoteKeysDao
import id.usereal.suitproject.data.local.remote_mediator.UserRemoteMediator
import id.usereal.suitproject.data.local.room.UserRoomDatabase
import id.usereal.suitproject.data.local.room.UsersDao
import id.usereal.suitproject.data.model.DataItem
import id.usereal.suitproject.data.model.UserResponse
import id.usereal.suitproject.data.remote.ApiService
import id.usereal.suitproject.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val usersDao: UsersDao,
    private val userRoomDatabase: UserRoomDatabase,
    private val remoteKeysDao: RemoteKeysDao
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getAllStories(): Flow<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            remoteMediator = UserRemoteMediator(userRoomDatabase, apiService),
            pagingSourceFactory = { userRoomDatabase.usersDao().getAllUser() }
        ).flow
    }
}