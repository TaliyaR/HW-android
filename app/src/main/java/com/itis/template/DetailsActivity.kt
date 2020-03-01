package com.itis.template

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
                tv_clouds.text = response.clouds.all.toString().plus("\u0025")
            } catch (ex: Exception) {
            }
        }
    }

    companion object {
        private const val KEY_ID = "id"

        fun createIntent(activity: Activity, id: Int) =
            Intent(activity, DetailsActivity::class.java).putExtra(KEY_ID, id)
    }
}
