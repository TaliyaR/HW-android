package com.itis.template.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.template.response.WeatherResponse

class WeatherItemAdapter(
    private var dataSource: List<WeatherResponse>,
    private val clickLambda: (WeatherResponse) -> Unit
) : RecyclerView.Adapter<WeatherItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItemHolder =
        WeatherItemHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: WeatherItemHolder, position: Int) {
        holder.bind(dataSource[position])
    }
}
