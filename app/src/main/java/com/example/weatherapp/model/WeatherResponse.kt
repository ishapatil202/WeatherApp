// Created by Pablo Casas – handles the API response data model

package com.example.weatherapp.model

data class WeatherResponse(
    val name: String, // City name
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int
)

data class Weather(
    val main: String,
    val description: String,
    val icon: String
)

data class Wind(
    val speed: Double
)
