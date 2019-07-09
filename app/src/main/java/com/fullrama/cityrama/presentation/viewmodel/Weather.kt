package com.mtp.weather.presentation.viewmodel

import com.mtp.weather.presentation.viewmodel.WeatherDetails

data class Weather(val latitude: Double,
                   val longitude: Double,
                   val timezone: String,
                   val details: WeatherDetails
)
