package id.usereal.suitproject.ui.screens.third_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.usereal.suitproject.data.model.UserResponse
import id.usereal.suitproject.repository.UserRepository
import id.usereal.suitproject.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThirdViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private val _usersState = MutableStateFlow<UiState<UserResponse>>(UiState.Loading)
    val usersState: StateFlow<UiState<UserResponse>> = _usersState.asStateFlow()

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            _usersState.value = UiState.Loading

            try {
                userRepository.getUsers()
                    .catch { exception ->
                        _usersState.value = UiState.Error(exception.message ?: "An unexpected error occurred")
                    }
                    .collect { response ->
                        _usersState.value = response
                    }
            } catch (e: Exception) {
                _usersState.value = UiState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }
}