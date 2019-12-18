package com.itis.template

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SongsAdapter(
        private val dataSource: List<Song>,
        private val clickLambda: (Song) -> Unit
) : RecyclerView.Adapter<SongsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsHolder = SongsHolder.create(parent, clickLambda)

    override fun onBindViewHolder(holder: SongsHolder, position: Int) = holder.bind(dataSource[position])

    override fun getItemCount(): Int = dataSource.size

}
