 package com.fullrama.cityrama.presentation.activity

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fullrama.cityrama.R
import com.mtp.weather.presentation.presenter.WeatherPresenter
import com.mtp.weather.presentation.view.IWeatherView
import com.mtp.weather.presentation.viewmodel.Weather
import kotlinx.android.synthetic.main.activity_weather.*


 class WeatherActivity : AppCompatActivity(), IWeatherView  {

     private var presenter: WeatherPresenter? = null
     private var locationManager : LocationManager? = null


     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
         // check the location
         // Create persistent LocationManager reference
         locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?;
             try {
                 // Request location updates
                 locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener);
             } catch(ex: SecurityException) {
                 Log.d("myTag", "Security Exception, no location available");
         }
         presenter = WeatherPresenter(this)

     }


     override fun renderWeather(data: Weather) {

         when (data.details.summary) {
             "Partly Cloudy" ->
                 imageCloud.setImageDrawable(resources.getDrawable(R.drawable.froid));
             else ->
                 imageCloud.setImageDrawable(resources.getDrawable(R.drawable.clear_sun));
         }
         status.text = "It's ${data.details.summary.toLowerCase()} where you are!"
         temp.text = getTemperatureMax(data.details.temperature).toString()
         humidity.text = (data.details.humidity * 100).toString()
         location.text =data.timezone

     }
     fun getTemperatureMax( a: Float): Float {
         val celsius = (a - 32.0) * 0.5555
         return Math.round(celsius!!).toFloat()
     }

     override fun showError(msg: String) {
         //TOAST AS THIS IS JUST A TECHNICAL TEST
         Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
     }

     override fun showNetworkError() {
         //TOAST AS THIS IS JUST A TECHNICAL TEST
         Toast.makeText(baseContext, "No internet. OMG, it's over!", Toast.LENGTH_LONG).show()
     }

     override fun onDestroy() {
         presenter?.destroy()
         presenter = null
         super.onDestroy()
     }


     //define the listener
     private val locationListener: LocationListener = object : LocationListener {
         override fun onLocationChanged(location: Location) {
             presenter?.getWeatherData(location?.latitude!!, location.longitude)
         }
         override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
         override fun onProviderEnabled(provider: String) {}
         override fun onProviderDisabled(provider: String) {}
     }

}
