package com.mtp.weather.presentation.presenter

import com.fullrama.cityrama.FullramaApplication
import com.mtp.weather.domain.interactor.GetWeather
import com.mtp.weather.presentation.view.IWeatherView
import com.mtp.weather.presentation.viewmodel.Weather
import com.ramijemli.klarna.util.ConnectivityHelper
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableSingleObserver
import java.lang.ref.WeakReference

class WeatherPresenter(weatherView: IWeatherView) : IPresenter {

    private lateinit var getWeather: GetWeather
    private var view: WeakReference<IWeatherView>? = null

    init {
        view = WeakReference(weatherView)
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        getWeather.dispose()

        view?.clear()
        view = null
    }

    fun getWeatherData(lat: Double, lng: Double) {
        if (!ConnectivityHelper.isConnected(FullramaApplication.instance.applicationContext)) {
            view?.get()?.showNetworkError()
            return
        }

        getWeather = GetWeather(lat, lng)
        getWeather.execute(object : DisposableSingleObserver<Weather>() {
            override fun onSuccess(@NonNull data: Weather) {
                view?.get()?.renderWeather(data)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                view?.get()?.showError("Houston, we have a problem!")
//                EXAMPLES OF ERROR HANDLING
//                when (e) {
//                    is AuthException -> view?.get()?.authError()
//                    is ParamException -> view?.get()?.showError(e.message!!)
//                    is ServerException -> view?.get()?.showError(e.message!!)
//                    is HttpException -> when (e.code()) {
//                        500 -> view?.get()?.showError(errorMsg)
//                        504 -> view?.get()?.showError(errorMsg)
//                    }
//                }
            }
        })
    }

}
