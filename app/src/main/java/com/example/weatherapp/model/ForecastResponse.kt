// added by Isha Patil
package com.example.weatherapp.model

data class ForecastResponse(
    val list: List<ForecastItem>
)

data class ForecastItem(
    val dt_txt: String, // "2025-05-05 15:00:00"
    val main: ForecastMain,
    val weather: List<ForecastWeather>
)

data class ForecastMain(
    val temp_min: Double,
    val temp_max: Double
)

data class ForecastWeather(
    val main: String,
    val icon: String
)


