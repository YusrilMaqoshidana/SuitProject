package id.usereal.suitproject.ui.screens.third_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import id.usereal.suitproject.R
import id.usereal.suitproject.common.avatar.Avatar
import id.usereal.suitproject.common.avatar.AvatarUser
import id.usereal.suitproject.common.card.CardComponent
import id.usereal.suitproject.data.model.UserResponse
import id.usereal.suitproject.utils.UiState

@Composable
fun ThirdScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ThirdViewModel = hiltViewModel()
) {
    val usersState by viewModel.usersState.collectAsStateWithLifecycle()

    ThirdContent(
        modifier = modifier,
        usersState = usersState, navController = navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    usersState: UiState<UserResponse> // Pass data here
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Third Screen",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W600
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_backstack),
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
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (usersState) {
                is UiState.Loading -> {
                    Text(text = "Loading...", fontSize = 16.sp)
                }

                is UiState.Success -> {
                    LazyColumn {
                        items(usersState.data.data) { user -> // 'data' adalah List<DataItem>
                            CardComponent(
                                title = "${user.firstName} ${user.lastName}",
                                subtitle = user.email,
                                leading = {
                                    AvatarUser(imageUrl = user.avatar)
                                },
                                onClick = {
                                    navController.navigate("second_screen/${user.firstName} ${user.lastName}")
                                }
                            )
                        }
                    }
                }

                is UiState.Error -> {
                    Text(text = "Error: ${usersState.message}", fontSize = 16.sp, color = Color.Red)
                }
            }
        }
    }
}