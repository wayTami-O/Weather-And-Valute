package com.example.viewpagerapp.api.valute

import com.example.viewpagerapp.model.valute.Money
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class ApiRequests(private val scope: CoroutineScope) {
    private val apiValute = ApiValute

    suspend fun getMoneyAsync() : Deferred<Money> {
        return scope.async {
            apiValute.newInstance().getDaily()
        }
    }

    //suspend fun getWeatherAsync(lat: Double, lon: Double): Deferred<Weather> {
    //    return  scope.async {
    //        apiWeather.newInstance().getWeather(lat, lon)
    //    }
    //}
}