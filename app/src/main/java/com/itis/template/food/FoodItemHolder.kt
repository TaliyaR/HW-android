package com.itis.template.food

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.template.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_food.view.*


class FoodItemHolder(
        override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(food: Food) {
        with(itemView) {
            tv_titlef.text = food.title
            tv_descriptionf.text = food.description
            iv_image.setImageResource(food.image)
        }
    }

    companion object {
        fun create(parent: ViewGroup) = FoodItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        )
    }
}
