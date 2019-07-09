package com.mtp.weather.domain.interactor

import com.fullrama.cityrama.data.repository.IWeatherRepository
import com.fullrama.cityrama.data.repository.WeatherRepository
import com.mtp.weather.presentation.viewmodel.Weather
import com.mtp.weather.presentation.viewmodel.WeatherDetails

import io.reactivex.Single

class GetWeather(private val lat: Double, private val lng: Double) : RxUseCase<Weather>() {

    private val repository: IWeatherRepository

    init {
        this.repository = WeatherRepository()
    }

    override fun buildUseCase(): Single<Weather> {
        return this.repository.get(lat, lng)
            .map { response ->
                val data = response.weatherDetails
                val details = WeatherDetails(
                    data.time,
                    data.summary,
                    data.icon,
                    data.precipIntensity,
                    data.precipProbability,
                    data.apparentTemperature,
                    data.apparentTemperature,
                    data.dewPoint,
                    data.humidity,
                    data.pressure,
                    data.windSpeed,
                    data.windGust,
                    data.windBearing,
                    data.cloudCover,
                    data.uvIndex,
                    data.visibility,
                    data.ozone
                )
                Weather(response.latitude, response.longitude, response.timezone, details)
            }
    }

}
