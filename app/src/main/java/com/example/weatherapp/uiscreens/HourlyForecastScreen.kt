package com.example.weatherapp.uiscreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.viewmodel.WeatherViewModel

@Composable
fun HourlyForecastScreen(viewModel: WeatherViewModel) {
    val forecast = viewModel.forecast.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Hourly Forecast", style = MaterialTheme.typography.headlineMedium)

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(forecast.take(24)) { item -> // limit to next 24 forecasts (3-hour intervals)
                val time = item.dt_txt.substring(11, 16) // HH:mm
                val temp = "${item.main.temp_max.toInt()}°C"
                val iconCode = item.weather.firstOrNull()?.icon ?: "01d"
                val iconUrl = "https://openweathermap.org/img/wn/$iconCode@2x.png"

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = time, modifier = Modifier.weight(1f))
                    AsyncImage(
                        model = iconUrl,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                    Text(text = temp, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}