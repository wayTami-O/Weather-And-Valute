package com.example.viewpagerapp.api.weather

import com.example.viewpagerapp.api.weather.ApiWeather
import com.example.viewpagerapp.model.weather.Weather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class ApiReqWeather(private val scope: CoroutineScope) {

    private var apiWeather = ApiWeather

    suspend fun getWeatherAsync(lat: Double, lon: Double) : Deferred<Weather> {
        return scope.async {
            apiWeather.newInstance().getWeather(lat, lon)
        }
    }
}