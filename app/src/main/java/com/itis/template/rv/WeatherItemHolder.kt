package com.itis.template.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.itis.template.R
import com.itis.template.response.WeatherResponse
import kotlinx.android.synthetic.main.item_weather.view.*
import kotlin.math.roundToInt


class WeatherItemHolder(
    val containerView: View,
    private val clickLambda: (WeatherResponse) -> Unit
) : RecyclerView.ViewHolder(containerView) {

    fun bind(weatherResponse: WeatherResponse) {
        itemView.tv_city.text = weatherResponse.name
        itemView.tv_temp.text = weatherResponse.main.temp.roundToInt().toString().plus("\u2103")
        weatherResponse.main.temp.let {
            @ColorInt val colorInt = when {
                it <= -20 -> {
                    R.color.darkBlue
                }
                (it > -20) and (it < 0) -> {
                    R.color.blue
                }
                it == 0.0 -> {
                    R.color.green
                }
                (it > 0) and (it < 20) -> {
                    R.color.orange
                }
                else -> {
                    R.color.red
                }
            }
            itemView.tv_temp.setTextColor(ContextCompat.getColor(itemView.context, colorInt))
        }
        itemView.setOnClickListener {
            clickLambda(weatherResponse)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (WeatherResponse) -> Unit) =
            WeatherItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false),
                clickLambda
            )
    }
}
