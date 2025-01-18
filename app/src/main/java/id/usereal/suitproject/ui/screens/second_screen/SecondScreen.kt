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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Second Screen",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W600
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_backstack), // Ganti dengan ikon di drawable
                                contentDescription = "Back"
                            )
                        }
                    }
                )
                HorizontalDivider(color = Color.Gray.copy(alpha = 0.2f), thickness = 1.dp)
            }
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
                onClick = {
                    navController.navigate("third_screen")
                },
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}
