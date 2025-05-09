// Written by Rutik Narute – Repository to fetch weather data
package com.example.weatherapp.repository

import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.RetrofitInstance
import com.example.weatherapp.model.ForecastResponse


class WeatherRepository {
    suspend fun getWeather(cityName: String, apiKey: String): WeatherResponse {
        return RetrofitInstance.api.getCurrentWeather(cityName, apiKey)
    }

    suspend fun getForecast(city: String, apiKey: String): ForecastResponse {
        return RetrofitInstance.api.getFiveDayForecast(city, apiKey)
    }

}