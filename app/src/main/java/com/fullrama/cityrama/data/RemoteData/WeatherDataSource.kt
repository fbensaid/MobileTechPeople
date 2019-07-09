package com.fullrama.cityrama.data.RemoteData

import com.fullrama.cityrama.data.rest.ApiEndpoints
import com.mtp.weather.data.rest.response.WeatherResponse
import io.reactivex.Single
import retrofit2.Retrofit

class WeatherDataSource : RemoteDataSource() {

    private var service: ApiEndpoints? = null

    override fun configEndpoint(retrofit: Retrofit) {
        service = retrofit.create(ApiEndpoints::class.java)
    }

    fun get(lat: Double, lng: Double): Single<WeatherResponse> {
        return service?.getWeather(lat, lng)!!
    }

}
