package id.usereal.suitproject

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.usereal.suitproject.ui.screens.first_screen.FirstScreen
import id.usereal.suitproject.ui.screens.second_screen.SecondScreen
import id.usereal.suitproject.ui.screens.third_screen.ThirdScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "first_screen") {
        composable("first_screen") {
            FirstScreen(navController = navController)
        }
        composable("second_screen/{name}?selectedUser={selectedUser}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val nameUser = backStackEntry.arguments?.getString("selectedUser") ?: "Selected User Name"
            SecondScreen(
                navController = navController, name = name,
                nameUser = nameUser
            )
        }
        composable("third_screen/{name}") {
            val name = it.arguments?.getString("name") ?: ""
            ThirdScreen(
                navController = navController,
                name = name,
            )
        }
    }
}