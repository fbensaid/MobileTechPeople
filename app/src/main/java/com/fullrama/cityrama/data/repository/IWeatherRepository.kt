package com.fullrama.cityrama.data.repository

import com.mtp.weather.data.rest.response.WeatherResponse
import io.reactivex.Single

interface IWeatherRepository {

    fun get(lat: Double, lng: Double): Single<WeatherResponse>

}

