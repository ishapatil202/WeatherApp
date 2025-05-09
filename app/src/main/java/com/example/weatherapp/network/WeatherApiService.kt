// Implemented by Rishab Lakhotra – Retrofit interface for OpenWeatherMap
package com.example.weatherapp.network

import com.example.weatherapp.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.weatherapp.model.ForecastResponse


interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherResponse

    @GET("data/2.5/forecast")
    suspend fun getFiveDayForecast(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): ForecastResponse

}