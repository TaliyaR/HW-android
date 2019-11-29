package com.itis.template.book

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil

class BookDiffUtilCallBack(
        private val oldList: List<Book>,
        private val newList: List<Book>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].description == newList[newItemPosition].description

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val diffBundle = Bundle()
        if (oldList[oldItemPosition].title != newList[newItemPosition].title) {
            diffBundle.putString("title", newList[newItemPosition].title)
        }
        if (oldList[oldItemPosition].description != newList[newItemPosition].description) {
            diffBundle.putString("description", newList[newItemPosition].description)
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }
}
