package id.usereal.suitproject.ui.screens.first_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor() : ViewModel(){
    fun isPalindrome(input: String): Boolean {
        val cleanInput = input.replace(Regex("[^A-Za-z0-9]"), "").lowercase()
        return cleanInput == cleanInput.reversed()
    }

    fun validation(name: String): Boolean {
        return name.isNotEmpty()
    }
}