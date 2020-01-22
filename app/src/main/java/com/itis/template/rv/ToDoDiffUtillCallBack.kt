package com.itis.template.rv

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.itis.template.entity.Task

class ToDoDiffUtillCallBack(
        private val oldList: List<Task>,
        private val newList: List<Task>
) : DiffUtil.Callback() {
    override fun getNewListSize(): Int = newList.size

    override fun getOldListSize(): Int = oldList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].title == newList[newItemPosition].title

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
