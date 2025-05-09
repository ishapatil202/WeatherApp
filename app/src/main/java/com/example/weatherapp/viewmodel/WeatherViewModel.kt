// Created by Rishab Lakhotra – ViewModel to manage weather state and trigger API calls
package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log
import com.example.weatherapp.model.ForecastItem


class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()
    private val apiKey = "9454931940f4afa74ebb3e9eb8a1a7a5"

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    private val _searchHistory = MutableStateFlow<List<String>>(emptyList())
    val searchHistory: StateFlow<List<String>> = _searchHistory

    private val _forecast = MutableStateFlow<List<ForecastItem>>(emptyList())
    val forecast: StateFlow<List<ForecastItem>> = _forecast

    fun getForecast(city: String) {
        viewModelScope.launch {
            try {
                val response = repository.getForecast(city, apiKey)
                _forecast.value = response.list
            } catch (e: Exception) {
                _forecast.value = emptyList()
            }
        }
    }



    fun getWeather(city: String) {
        viewModelScope.launch {
            try {
                Log.d("WeatherDebug", "Fetching weather for city: $city")
                val response = repository.getWeather(city, apiKey)

                // Debug: Response content
                Log.d("WeatherDebug", "Received response: ${response.name}, Temp: ${response.main.temp}")

                _weather.value = repository.getWeather(city, apiKey)
                _searchHistory.value = _searchHistory.value + city

                val forecastResponse = repository.getForecast(city, apiKey)
                _forecast.value = forecastResponse.list

            } catch (e: Exception) {
                Log.e("WeatherDebug", "API call failed: ${e.localizedMessage}")
                _weather.value = null // could add error state handling
            }

        }
    }
}
