// Details screen UI by Rutik Narute – shows extra weather info
package com.example.weatherapp.uiscreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.viewmodel.WeatherViewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow

@Composable
fun DetailsScreen(viewModel: WeatherViewModel) {
    val weather = viewModel.weather.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Weather Details", style = MaterialTheme.typography.headlineMedium)

        weather?.let {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    DetailItem(label = "Feels Like", value = "${it.main.feels_like}°C")
                    DetailItem(label = "Humidity", value = "${it.main.humidity}%")
                    DetailItem(label = "Pressure", value = "${it.main.pressure} hPa")
                    DetailItem(label = "Wind Speed", value = "${it.wind.speed} m/s")
                }
            }
        } ?: Text("No weather data available.")
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}
