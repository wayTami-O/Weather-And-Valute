package com.example.viewpagerapp.model.weather

data class DayShort(
    val _source: String,
    val cloudness: Double,
    val condition: String,
    val daytime: String,
    val feels_like: Int,
    val fresh_snow_mm: Int,
    val humidity: Int,
    val icon: String,
    val polar: Boolean,
    val prec_mm: Double,
    val prec_period: Int,
    val prec_prob: Int,
    val prec_strength: Double,
    val prec_type: Int,
    val pressure_mm: Int,
    val pressure_pa: Int,
    val soil_moisture: Double,
    val soil_temp: Int,
    val temp: Int,
    val temp_min: Int,
    val temp_water: Int,
    val uv_index: Int,
    val wind_dir: String,
    val wind_gust: Double,
    val wind_speed: Double
)