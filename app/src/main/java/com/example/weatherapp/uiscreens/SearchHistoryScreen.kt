// Screen implemented by Pablo Casas – displays and reuses past searches
package com.example.weatherapp.uiscreens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.viewmodel.WeatherViewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow

@Composable
fun SearchHistoryScreen(
    viewModel: WeatherViewModel,
    onCitySelected: (String) -> Unit
) {
    val history = viewModel.searchHistory.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Search History", style = MaterialTheme.typography.headlineMedium)

        if (history.value.isEmpty()) {
            Text("No recent searches.")
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    history.value.reversed().forEach { city ->
                        Text(
                            text = city,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onCitySelected(city) }
                                .padding(vertical = 8.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Divider()
                    }
                }
            }
        }
    }
}
