package id.usereal.suitproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import id.usereal.suitproject.data.model.UserResponse
import id.usereal.suitproject.data.remote.ApiService
import id.usereal.suitproject.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {
    fun getUsers(): Flow<UiState<UserResponse>> = flow {
        emit(UiState.Loading)
        try {
            val response = apiService.getUsers()
            if (response.data.isNotEmpty()) {
                emit(UiState.Success(response))
            } else {
                emit(UiState.Error("Data not found"))
            }
        } catch (e: Exception) {
            emit(UiState.Error(e.message.toString()))
        }
    }
}