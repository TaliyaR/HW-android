package com.itis.template

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class BookItemHolder(
        override val containerView: View,
        private val clickLambda: (Book) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(book: Book) {
        with(itemView) {
            tv_title.text = book.title
            tv_description.text = book.description

            delete_btn.setOnClickListener {
                clickLambda(book)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (Book) -> Unit) =
                BookItemHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false),
                        clickLambda
                )
    }
}
