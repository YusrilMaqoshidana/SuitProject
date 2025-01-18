package id.usereal.suitproject.ui.screens.first_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.usereal.suitproject.R
import id.usereal.suitproject.common.avatar.Avatar
import id.usereal.suitproject.common.button.LongButton
import id.usereal.suitproject.common.textfield.TextFieldGeneral
import kotlinx.coroutines.launch

@Composable
fun FirstScreen(modifier: Modifier = Modifier, viewModel: FirstViewModel = hiltViewModel()) {
    var name by remember { mutableStateOf("") }
    var palindrome by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Palindrome Check") },
            text = { Text(text = dialogMessage) },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = "OK")
                }
            }
        )
    }

    FirstContent(
        name = name,
        palindrome = palindrome,
        onNameChange = { name = it },
        onPalindromeChange = { palindrome = it },
        modifier = modifier,
        onCheckClick = {
            val isPalindrome = viewModel.isPalindrome(palindrome)
            dialogMessage = if (isPalindrome) {
                "\"$palindrome\" isPalindrome"
            } else {
                "\"$palindrome\" not palindrome"
            }
            showDialog = true
        },
        onNextClick = {
            val isNameNotEmpty = viewModel.validation(name)
            if (isNameNotEmpty) {
                // Lakukan sesuatu jika nama tidak kosong
            } else {
                scope.launch {
                    snackbarHostState.showSnackbar("Name cannot be empty!")
                }
            }
        },
        snackbarHostState = snackbarHostState
        )
}

@Composable
fun FirstContent(
    modifier: Modifier = Modifier,
    name: String = "",
    palindrome: String = "",
    onNameChange: (String) -> Unit = {},
    onPalindromeChange: (String) -> Unit = {},
    onCheckClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    snackbarHostState: SnackbarHostState
) {

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = modifier,
    ) { innerPadding ->
        // Konten utama
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.background_image),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Avatar(
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .size(150.dp)
                )
                TextFieldGeneral(
                    title = "Name",
                    value = name,
                    onValueChange = onNameChange,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                TextFieldGeneral(
                    title = "Palindrome",
                    value = palindrome,
                    onValueChange = onPalindromeChange,
                    modifier = Modifier
                )
                LongButton(
                    text = "Check",
                    onClick = onCheckClick,
                    modifier = Modifier.padding(top = 40.dp)
                )
                LongButton(
                    text = "Next",
                    onClick = onNextClick,
                )
            }
        }
    }
}

