package id.usereal.suitproject

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.usereal.suitproject.ui.screens.first_screen.FirstScreen
import id.usereal.suitproject.ui.screens.second_screen.SecondScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "first_screen") {
        composable("first_screen") {
            FirstScreen(navController = navController)
        }
        composable("second_screen/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            SecondScreen(navController = navController, name = name)
        }
    }
}