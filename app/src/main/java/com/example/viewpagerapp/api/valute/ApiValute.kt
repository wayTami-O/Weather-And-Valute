package com.example.viewpagerapp.api.valute

import com.example.viewpagerapp.model.valute.Money
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface ApiValute {

    @GET("daily_json.js")
    suspend fun getDaily(): Money

    companion object {
        fun newInstance(baseUrl: String = "https://www.cbr-xml-daily.ru/"): ApiValute = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}