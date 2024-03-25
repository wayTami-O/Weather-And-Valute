package com.example.viewpagerapp.api.weather

import com.example.viewpagerapp.model.weather.Weather
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiWeather {

    @GET("forecast?")
    @Headers("X-Yandex-Weather-Key:98d8c4c1-c2f0-40e4-94e7-b025c97cc5dc")
    suspend fun getWeather(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("lang") lang: String = "ru_RU"): Weather

    companion object {
        fun newInstance(baseUrl: String = "https://api.weather.yandex.ru/v2/"): ApiWeather = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}