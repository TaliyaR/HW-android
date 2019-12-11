package com.itis.template

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_songs.view.*

class SongsHolder(override val containerView: View,
                  private val clickLambda: (Song) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(song: Song) {
        containerView.apply {
            tv_title.text = song.title
            tv_artist.text = song.artist
            iv_cover.setImageResource(song.cover)



            itemView.setOnClickListener {
                clickLambda(song)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (Song) -> Unit) =
                SongsHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_songs, parent, false),
                        clickLambda
                )
    }
}
