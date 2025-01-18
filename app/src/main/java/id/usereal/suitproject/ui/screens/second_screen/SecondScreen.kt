package id.usereal.suitproject.ui.screens.second_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.usereal.suitproject.R
import id.usereal.suitproject.common.button.LongButton

@Composable
fun SecondScreen(navController: NavController, name: String, modifier: Modifier = Modifier) {
    SecondContent(
        navController = navController,
        name = name
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondContent(modifier: Modifier = Modifier, navController: NavController, name: String) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Second Screen",
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_backstack), // Ganti dengan nama file ikon di drawable
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp) // Sesuaikan ukuran ikon jika diperlukan
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()

            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Welcome")
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
            }
            Text(
                text = "Selected User Name",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.fillMaxWidth()
            )
            LongButton(
                text = "Choose a User",
                onClick = {  },
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}
