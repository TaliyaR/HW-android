package com.itis.template.food

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.template.R
import com.itis.template.food.ImageAdapter.Companion.imageAdapter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_food.view.*


class FoodItemHolder(
        override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(food: Food) {
        containerView.apply {
            tv_title.text = food.title
            tv_description.text = food.description
            iv_circle.setImageResource(food.image)
            view_pager.adapter = imageAdapter(context, food.images)
            dots.attachViewPager(view_pager)
        }
    }

    companion object {
        fun create(parent: ViewGroup) = FoodItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        )
    }
}
