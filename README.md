# WeatherApp – Kotlin-Based Functional Weather Forecast App

A Kotlin Android app that delivers real-time weather data using Jetpack Compose and Retrofit, applying functional programming principles and modern UI architecture.

---

## App Summary

**WeatherApp** allows users to:
- Search for any city and get up-to-date weather data (temperature, humidity, wind, etc.)
- View recent search history and revisit previously searched cities
- Receive clothing suggestions based on the temperature ("What to Wear Today")
- Navigate between Home, Details, Search History, and About screens

The app is structured using **MVVM** architecture and written entirely in **Kotlin** using **Jetpack Compose** for UI.

---

## Setup / Build / Run Instructions

### Requirements:
- Android Studio Giraffe or later
- Kotlin 1.8+
- Minimum SDK: 24 (Android 7.0)
- Internet connection for API access

### Steps:

1. Clone this repo:

2. Open the project in Android Studio

3. Replace `YOUR_API_KEY` in `WeatherViewModel.kt` with your key from:
   [https://openweathermap.org/api](https://openweathermap.org/api)

4. Build and run the app:
   - Run on Android Emulator (API 30+)
   - Or connect and run on a physical device

---

## Dependencies Used

| Library                | Purpose                            |
|------------------------|-------------------------------------|
| Jetpack Compose        | Declarative UI components           |
| Retrofit               | HTTP networking & API integration   |
| Gson                   | JSON parsing for weather data       |
| Kotlin Coroutines      | Asynchronous programming            |
| StateFlow              | Reactive state management           |
| Navigation-Compose     | Screen-to-screen navigation         |

---


## Acknowledgments

- Weather data provided by [OpenWeatherMap API](https://openweathermap.org/api)
- Kotlin & Jetpack Compose documentation: [https://developer.android.com/jetpack/compose](https://developer.android.com/jetpack/compose)
- Functional programming inspiration from CS5035 course lectures
- Icons and emoji from [Material Icons](https://fonts.google.com/icons) and Unicode

---

## Features Demonstrated (CS5035 Requirements)

- Immutability
- Lambdas and higher-order functions
- Safe calls and null handling
- Declarative UI with Compose
- State management using StateFlow
- MVVM architecture
- At least 3 functional screens (Home, Details, History)

---
