package com.itis.template

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.coroutines.*
import kotlin.math.roundToInt

class DetailsActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var service: WeatherService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val id = intent.extras?.getInt(KEY_ID) ?: 0

        service = ApiFactory.weatherService
        responseById(id)
    }

    private fun responseById(id: Int) {
        launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    service.weatherByID(id)
                }
                supportActionBar?.setTitle(response.name)
                tv_temp.text = response.main.temp.roundToInt().toString().plus("\u2103")
                tv_humidity.text = response.main.humidity.toString().plus("\u0025")
                tv_main.text = response.weather[0].description
                tv_wind.text = response.wind.deg.toString()
                response.wind.deg.let {
                    val windDeg = when {
                        (it > 0) and (it < 90) -> {
                            "NE"
                        }
                        (it > 90) and (it < 180) -> {
                            "SE"
                        }
                        (it > 180) and (it < 270) -> {
                            "SW"
                        }
                        (it > 270) and (it < 360) -> {
                            "NW"
                        }
                        it == 90 -> {
                            "E"
                        }
                        it == 180 -> {
                            "S"
                        }
                        it == 270 -> {
                            "W"
                        }
                        else -> {
                            "N"
                        }
                    }
                    tv_wind.text = windDeg
                }
                tv_clouds.text = response.clouds.all.toString().plus("\u0025")
                Glide.with(this@DetailsActivity)
                    .load(getIconUrl(response.weather.get(0).icon))
                    .into(iv_weather)
            } catch (ex: Exception) {
            }
        }
    }

    private fun getIconUrl(id: String?) = "http://openweathermap.org/img/wn/$id@2x.png"

    companion object {
        private const val KEY_ID = "id"

        fun createIntent(activity: Activity, id: Int) =
            Intent(activity, DetailsActivity::class.java).putExtra(KEY_ID, id)
    }
}
