package com.itis.template.food

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(
        private var dataSource: List<Food>
) : RecyclerView.Adapter<FoodItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemHolder =
            FoodItemHolder.create(parent)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: FoodItemHolder, position: Int) = holder.bind(dataSource[position])

}
