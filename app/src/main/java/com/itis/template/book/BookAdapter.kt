package com.itis.template.book

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(
        private var dataSource: ArrayList<Book>
) : RecyclerView.Adapter<BookItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemHolder =
            BookItemHolder.create(parent) {
                ListBook.deleteItem(it)
                update()
            }

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: BookItemHolder, position: Int) = holder.bind(dataSource[position])

    fun update() {
        val diffCallBack = BookDiffUtilCallBack(dataSource, ListBook.getList())
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        dataSource.apply {
            clear()
            addAll(ListBook.getList())
        }
        diffResult.dispatchUpdatesTo(this)
    }
}

