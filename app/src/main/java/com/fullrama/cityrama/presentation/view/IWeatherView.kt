package com.mtp.weather.presentation.view

import com.mtp.weather.presentation.viewmodel.Weather

interface IWeatherView {
    fun renderWeather(data: Weather)
    fun showError(msg: String)
    fun showNetworkError()
}
