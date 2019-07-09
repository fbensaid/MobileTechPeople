package com.fullrama.cityrama.data.repository

import com.fullrama.cityrama.data.RemoteData.WeatherDataSource
import com.mtp.weather.data.rest.response.WeatherResponse
import io.reactivex.Single

class WeatherRepository : IWeatherRepository {

    private var remoteDataSource: WeatherDataSource? = null

    override fun get(lat: Double, lng: Double): Single<WeatherResponse> {
        this.remoteDataSource = WeatherDataSource()
        return this.remoteDataSource?.get(lat, lng)!!
    }

}
