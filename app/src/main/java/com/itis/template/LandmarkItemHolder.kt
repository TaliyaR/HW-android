package com.itis.template

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_landmark.view.*

class LandmarkItemHolder(override val containerView: View,
                         private val clickLambda: (Landmark) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(landmark: Landmark) {

        itemView.tv_name.text = landmark.name
        itemView.tv_description.text = landmark.description
        itemView.iv_image.setImageResource(landmark.image)

        itemView.setOnClickListener {
            clickLambda(landmark)
        }
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (Landmark) -> Unit) =
                LandmarkItemHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_landmark, parent, false),
                        clickLambda
                )
    }

}