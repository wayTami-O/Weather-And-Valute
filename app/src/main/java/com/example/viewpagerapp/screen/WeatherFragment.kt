package com.example.viewpagerapp.screen

import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.viewpagerapp.R
import com.example.viewpagerapp.api.weather.ApiReqWeather
import com.example.viewpagerapp.databinding.FragmentWeatherBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.*
import java.util.Date

class WeatherFragment : Fragment() {
    
    private lateinit var fussedLocationProviderClient: FusedLocationProviderClient
    lateinit var binding: FragmentWeatherBinding
    private val scope = CoroutineScope(Dispatchers.IO)
    private val apiWeather = ApiReqWeather(scope)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(layoutInflater)
        fussedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        getLocation()
        binding.imageView2.setOnClickListener {
            Toast.makeText(requireContext(), "Спасибо Керик!", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 100)
            return
        }

        val location = fussedLocationProviderClient.lastLocation
        location.addOnSuccessListener {
            if (it!=null) {
                scope.launch(Dispatchers.Main) {
                    var timeNow = SimpleDateFormat("HH:mm").format(Date())
                    var weather = apiWeather.getWeatherAsync(it.latitude, it.longitude).await()
                    var weatherIcon = weather.fact.condition
                    activity?.runOnUiThread {
                        binding.time.text = timeNow.toString()
                        binding.textView.text = "Город: ${if (weather.geo_object.locality.name == "Сызрань") "${weather.geo_object.locality.name} (Пиздец)" else weather.geo_object.locality.name}"
                        binding.textIcon.text = adapterWeather(weatherIcon)
                        binding.temp.text = "${weather.fact.temp}℃"
                        binding.tempOsh.text = "Ощущается как: ${weather.fact.feels_like}℃"
                        binding.davl.text = "Давление: ${weather.fact.pressure_mm} мм рт. ст."
                        binding.vlagnost.text = "Влажность: ${weather.fact.humidity}%"
                        binding.imageView2.setImageResource(adapterPhoto(weatherIcon))
                        binding.speedWind.text = "Направление ветра: ${wind(weather.fact.wind_dir)}"
                        binding.napWind.text = "Скорость ветра: ${weather.fact.wind_gust} м/c"
                        binding.dayTime.text = "Время суток: ${getDayTime(weather.fact.daytime)}"
                        binding.yearTime.text = "Время года: ${getYearTime(weather.fact.season)}"
                        binding.cloudness.text = "Облачность: ${getCloudness(weather.fact.cloudness.toDouble())}"
                    }
                }
            }
        }
    }

    private fun adapterPhoto(desk: String): Int {
        var idImg = 0
        when(desk) {
            "clear" -> idImg = R.drawable.baseline_wb_sunny_24
            "partly-cloudy" -> idImg = R.drawable.partly_cloudy_svgrepo_com
            "cloudy", "overcast" -> idImg = R.drawable.baseline_wb_cloudy_24
            "light-rain" -> idImg = R.drawable.light_rain_svgrepo_com
            "rain" -> idImg = R.drawable.rain_2_svgrepo_com
            "heavy-rain", "showers" -> idImg = R.drawable.strong_rain_svgrepo_com
            "wet-snow" -> idImg = R.drawable.rain_snow_svgrepo_com
            "light-snow" -> idImg = R.drawable.light_snow_svgrepo_com
            "snow", "snow-showers" -> idImg = R.drawable.snow_alt_1_svgrepo_com
            "hail" -> idImg = R.drawable.hail_sleet_snowy_svgrepo_com
            "thunderstorm", "thunderstorm-with-rain", "thunderstorm-with-hail" -> idImg = R.drawable.thunderstorm_svgrepo_com
        }
        return idImg
    }

    private fun adapterWeather(desk: String): String {
        var idWeath = ""
        when(desk) {
            "clear" -> idWeath = "Ясно"
            "partly-cloudy" -> idWeath = "Переменная облачность"
            "cloudy", "overcast" -> idWeath = "Облачно"
            "light-rain" -> idWeath = "Небольшой дождь"
            "rain" -> idWeath = "Дождь"
            "heavy-rain", "showers" -> idWeath = "Сильный дождь"
            "wet-snow" -> idWeath = "Дождь со снегом"
            "light-snow" -> idWeath = "Небольшой снег"
            "snow", "snow-showers" -> idWeath = "Снегопад"
            "hail" -> idWeath = "Град"
            "thunderstorm", "thunderstorm-with-rain", "thunderstorm-with-hail" -> idWeath = "Гроза"
        }
        return idWeath
    }

    private fun wind(desk: String): String {
        var nameWeath = ""
        when (desk) {
                "nw" ->nameWeath = "Северо-западное"
                "n" ->nameWeath = "Северное"
                "ne" ->nameWeath = "Северо-восточное"
                "e" ->nameWeath = "Восточное"
                "se" ->nameWeath = "Юго-восточное"
                "s" ->nameWeath = "Южное"
                "sw" ->nameWeath = "Юго-западное"
                "w" ->nameWeath = "Западное"
                "c" ->nameWeath = "Штиль"
        }
        return nameWeath
    }

    private fun getDayTime(time: String): String {
        return when(time) {
            "d" -> "Светлое время суток"
            "n" -> "Тёмное время суток"
            else -> {"хз"}
        }
    }

    private fun getYearTime(timeYear: String): String {
        return when(timeYear) {
                "summer" -> "Лето"
                "autumn" -> "Осень"
                "winter" -> "Зима"
                "spring" -> "Весна"
            else -> {"хз"}
        }
    }

    private fun getCloudness(cloudPers: Double): String {
        return when(cloudPers) {
            0.0 -> "Ясно"
            0.25 -> "Малооблачно"
            0.5 -> "Облачно с прояснениями"
            0.75 -> "Облачно с прояснениями"
            1.0 -> "Пасмурно"
            else -> {"хз"}
        }
    }
}