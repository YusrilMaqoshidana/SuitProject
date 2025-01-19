package id.usereal.suitproject.ui.screens.third_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.usereal.suitproject.data.model.DataItem
import id.usereal.suitproject.data.model.UserResponse
import id.usereal.suitproject.repository.UserRepository
import id.usereal.suitproject.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThirdViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    val users: Flow<PagingData<DataItem>> =
        userRepository.getAllStories().cachedIn(viewModelScope)
}
