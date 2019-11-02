package com.itis.template

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LandmarkAdapter(
        private var dataSource: List<Landmark>,
        private val clickLambda: (Landmark) -> Unit
) : RecyclerView.Adapter<LandmarkItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkItemHolder =
            LandmarkItemHolder.create(parent, clickLambda)

    override fun onBindViewHolder(holder: LandmarkItemHolder, position: Int) {
        holder.bind(dataSource[position])
    }

    override fun getItemCount() = dataSource.size

}