package com.itis.template.book

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil

object DiffBook : DiffUtil.ItemCallback<Book>() {

    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
            oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
            oldItem == newItem

    override fun getChangePayload(oldItem: Book, newItem: Book): Any? {
        val diffBundle = Bundle()
        if (oldItem.title != newItem.title) {
            diffBundle.putString("title", newItem.title)
        }
        if (oldItem.description != newItem.description) {
            diffBundle.putString("description", newItem.description)
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }

}

