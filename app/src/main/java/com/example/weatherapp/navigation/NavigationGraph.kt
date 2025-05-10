// Navigation system created by Jordan Doose

package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.WeatherHomeScreen
import com.example.weatherapp.uiscreens.DetailsScreen
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.example.weatherapp.uiscreens.SearchHistoryScreen
import com.example.weatherapp.uiscreens.AboutScreen
import com.example.weatherapp.uiscreens.HourlyForecastScreen



sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Details : Screen("details")
    object History : Screen("history")
    object About : Screen("about")
    object Hourly : Screen("hourly")

}

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    val viewModel: WeatherViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            WeatherHomeScreen(navController, viewModel)
        }
        composable(Screen.Details.route) {
            DetailsScreen(viewModel)
        }
        composable(Screen.History.route) {
            SearchHistoryScreen(viewModel) { selectedCity ->
                viewModel.getWeather(selectedCity)
                navController.navigate(Screen.Home.route)
            }
        }
        composable(Screen.About.route) {
            AboutScreen()
        }
        composable(Screen.Hourly.route) {
            HourlyForecastScreen(viewModel)
        }



    }
}


