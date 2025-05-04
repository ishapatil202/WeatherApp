// Created by Rishab Lakhotra – ViewModel to manage weather state and trigger API calls
package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()
    private val apiKey = "9454931940f4afa74ebb3e9eb8a1a7a5"

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    private val _searchHistory = MutableStateFlow<List<String>>(emptyList())
    val searchHistory: StateFlow<List<String>> = _searchHistory


    fun getWeather(city: String) {
        viewModelScope.launch {
            try {
                _weather.value = repository.getWeather(city, apiKey)
                _searchHistory.value = _searchHistory.value + city

            } catch (e: Exception) {
                _weather.value = null // could add error state handling
            }
        }
    }
}
