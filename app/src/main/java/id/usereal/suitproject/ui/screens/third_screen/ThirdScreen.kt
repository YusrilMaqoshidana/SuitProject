@file:Suppress("DEPRECATION")

package id.usereal.suitproject.ui.screens.third_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import id.usereal.suitproject.R
import id.usereal.suitproject.common.avatar.AvatarUser
import id.usereal.suitproject.common.card.CardComponent
import id.usereal.suitproject.data.model.DataItem
import id.usereal.suitproject.utils.ShimmerListItem

@Composable
fun ThirdScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    name: String,
    viewModel: ThirdViewModel = hiltViewModel()
) {
    val usersPagingItems = viewModel.users.collectAsLazyPagingItems()
    ThirdContent(
        modifier = modifier,
        usersPagingItems = usersPagingItems,
        navController = navController,
        name = name
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    usersPagingItems: LazyPagingItems<DataItem>,
    name: String
) {
    val isRefreshing = usersPagingItems.loadState.refresh is LoadState.Loading
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

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
        }
    ) { innerPadding ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { usersPagingItems.refresh() }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when {
                    usersPagingItems.loadState.refresh is LoadState.Loading -> {
                        items(count = 10, key = { index -> "shimmer_$index" }) {
                            ShimmerListItem(isLoading = true)
                        }
                    }

                    usersPagingItems.loadState.refresh is LoadState.Error -> {
                        item(key = "refresh_error") {
                            val error = (usersPagingItems.loadState.refresh as LoadState.Error)
                            ErrorItem(
                                message = error.error.localizedMessage ?: "An error occurred",
                                onRetryClick = { usersPagingItems.retry() }
                            )
                        }
                    }
                    else -> {
                        items(
                            count = usersPagingItems.itemCount
                        ) { index ->
                            val user = usersPagingItems[index]
                            user?.let {
                                CardComponent(
                                    title = "${it.firstName} ${it.lastName}",
                                    subtitle = it.email,
                                    leading = { AvatarUser(imageUrl = it.avatar) },
                                    onClick = {
                                        navController.popBackStack()
                                        navController.navigate("second_screen/$name?selectedUser=${it.firstName} ${it.lastName}") {
                                            popUpTo("second_screen") { inclusive = true }
                                        }
                                    }
                                )
                            }
                        }



                        when (usersPagingItems.loadState.append) {
                            is LoadState.Loading -> {
                                item(key = "append_loading") {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp)
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier
                                                .align(Alignment.Center)
                                                .size(32.dp)
                                        )
                                    }
                                }
                            }

                            is LoadState.Error -> {
                                item(key = "append_error") {
                                    val error =
                                        (usersPagingItems.loadState.append as LoadState.Error)
                                    ErrorItem(
                                        message = error.error.localizedMessage
                                            ?: "Error loading more items",
                                        onRetryClick = { usersPagingItems.retry() }
                                    )
                                }
                            }

                            else -> {}
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorItem(
    message: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            color = Color.Red,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetryClick) {
            Text(text = "Retry")
        }
    }
}
