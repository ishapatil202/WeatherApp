// UI built by Isha patil– Home screen with city input and fetch button

package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.viewmodel.WeatherViewModel
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.navigation.AppNavigation
import com.example.weatherapp.navigation.Screen
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import com.airbnb.lottie.compose.*




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}

@Composable
fun WeatherHomeScreen(navController: androidx.navigation.NavHostController, viewModel: WeatherViewModel = viewModel()) {
    var city by remember { mutableStateOf("") }
    val weatherState = viewModel.weather.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        weatherState.value?.let { weather ->
            val weatherCondition = weather.weather.firstOrNull()?.main ?: "Clear"
            val animationRes = when (weatherCondition) {
                "Clear" -> R.raw.sunny
                "Rain", "Drizzle" -> R.raw.rain
                "Snow" -> R.raw.snow
                "Clouds" -> R.raw.cloudy
                else -> R.raw.sunny
            }

            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationRes))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .align(Alignment.TopStart)  // anchor to top
            ) {
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White.copy(alpha = 0.15f)) // adjust opacity if needed
                )

            }

        }
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 32.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Cloud,
                        contentDescription = "Weather Icon",
                        tint = Color(0xFFFFC107) // Amber
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Weather Forecast", style = MaterialTheme.typography.headlineMedium)
                }

                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    label = { Text("Enter city name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = { viewModel.getWeather(city) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text("Get Weather")
                }

                weatherState.value?.let { weather ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .shadow(4.dp, shape = RoundedCornerShape(12.dp)),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.6f)

                        )
                    ){
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("City: ${weather.name}", style = MaterialTheme.typography.titleLarge)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Temperature: ${weather.main.temp}°C", style = MaterialTheme.typography.bodyLarge)
                            Text("Condition: ${weather.weather.firstOrNull()?.main ?: "Unknown"}", fontStyle = FontStyle.Italic)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    val clothingSuggestion = when {
                        weather.main.temp < 10 -> "🥶 It's cold! Wear a jacket and layers."
                        weather.main.temp in 10.0..20.0 -> "🧥 Light sweater weather. Stay cozy!"
                        weather.main.temp in 20.0..30.0 -> "😎 It's warm! T-shirt and sunglasses recommended."
                        weather.main.temp > 30 -> "🔥 It's hot! Stay hydrated and wear light clothes."
                        else -> "🤔 Weather seems moderate. Dress comfortably."
                    }

                    Text(
                        text = clothingSuggestion,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Button(
                        onClick = { navController.navigate(Screen.Details.route) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Text("View Details")
                    }

                    Button(
                        onClick = { navController.navigate(Screen.Hourly.route) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Text("Hourly Forecast")
                    }


                    Button(
                        onClick = { navController.navigate(Screen.History.route) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Text("Search History")
                    }

                    Button(
                        onClick = { navController.navigate(Screen.About.route) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Text("About")
                    }


                }
            }
        }
    }