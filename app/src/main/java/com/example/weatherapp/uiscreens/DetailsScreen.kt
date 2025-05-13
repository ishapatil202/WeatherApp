//Detail screen added by rutik narute
package com.example.weatherapp.uiscreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.shadow
import coil.compose.AsyncImage
import com.example.weatherapp.viewmodel.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.Locale



@Composable
fun DetailsScreen(viewModel: WeatherViewModel) {
    val weather = viewModel.weather.value
    val forecastState = viewModel.forecast.collectAsState()
    val forecast = forecastState.value

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
                    .shadow(4.dp, shape = RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.6f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    WeatherDetailRow(label = "Feels Like", value = "${it.main.feels_like}°C")
                    Divider(color = Color.LightGray.copy(alpha = 0.3f))
                    WeatherDetailRow(label = "Humidity", value = "${it.main.humidity}%")
                    Divider(color = Color.LightGray.copy(alpha = 0.3f))
                    WeatherDetailRow(label = "Pressure", value = "${it.main.pressure} hPa")
                    Divider(color = Color.LightGray.copy(alpha = 0.3f))
                    WeatherDetailRow(label = "Wind Speed", value = "${it.wind.speed} m/s")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Weekly Forecast", style = MaterialTheme.typography.titleMedium)

            val forecastGrouped: Map<String, List<com.example.weatherapp.model.ForecastItem>> =
                (forecast as List<com.example.weatherapp.model.ForecastItem>).groupBy { it.dt_txt.substring(0, 10) }

            forecastGrouped.forEach { (date, items) ->
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val dateObj = sdf.parse(date)
                val dayOfWeek = SimpleDateFormat("EEE", Locale.getDefault()).format(dateObj ?: date)

                val minTemp = items.minOfOrNull { it.main.temp_min } ?: 0.0
                val maxTemp = items.maxOfOrNull { it.main.temp_max } ?: 0.0
                val iconCode = items.firstOrNull()?.weather?.firstOrNull()?.icon ?: "01d"
                val iconUrl = "https://openweathermap.org/img/wn/$iconCode@2x.png"

                ForecastRow(day = dayOfWeek, min = minTemp, max = maxTemp, iconUrl = iconUrl)
            }

        } ?: Text("No weather data available.")
    }
}

@Composable
fun WeatherDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyLarge)
        Text(value, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun ForecastRow(day: String, min: Double, max: Double, iconUrl: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(day, modifier = Modifier.weight(1f))


        AsyncImage(
            model = iconUrl,
            contentDescription = "Weather icon",
            modifier = Modifier
                .size(32.dp)
                .padding(horizontal = 4.dp)
        )

        Text("${min.toInt()}°", modifier = Modifier.weight(1f))

        LinearProgressIndicator(
            progress = (max / 40.0).toFloat().coerceIn(0.2f, 1f),
            modifier = Modifier
                .weight(3f)
                .height(6.dp),
            color = Color.Blue,
            trackColor = Color.LightGray.copy(alpha = 0.3f)
        )

        Text("${max.toInt()}°", modifier = Modifier.weight(1f))
    }
}
