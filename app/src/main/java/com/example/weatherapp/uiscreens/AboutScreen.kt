// About screen added by Jordan Doose – app overview and tech stack
package com.example.weatherapp.uiscreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("About This App", style = MaterialTheme.typography.headlineMedium)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("🌤️ WeatherApp is a Kotlin-based Android application that fetches real-time weather data using OpenWeatherMap API.")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Built using:")
                Text("- Kotlin & Jetpack Compose")
                Text("- Retrofit + Gson")
                Text("- MVVM architecture")

                Spacer(modifier = Modifier.height(8.dp))
                Text("Developed by:")
                Text("Isha Patil, Rishab Lakhotra, Rutik Narute, Pablo Casas, Jordan Doose")
            }
        }
    }
}
