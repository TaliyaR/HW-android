package com.itis.template

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(
        private var dataSource: List<Book>,
        private val clickLambda: (Book) -> Unit
) : RecyclerView.Adapter<BookItemHolder>() {

    private val books = mutableListOf<Book>()

    init {
        books.addAll(dataSource)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemHolder =
            BookItemHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: BookItemHolder, position: Int) = holder.bind(dataSource[position])

    fun update(books: List<Book>) {
        val diffCallBack = BookDiffUtilCallBack(this.books, books)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        this.books.clear()
        this.books.addAll(books)
        diffResult.dispatchUpdatesTo(this)
    }

    fun deleteItem(item: Book) {
        val books = this.books
        books.remove(item)
        update(books.toList())
    }

    fun deleteItem(position: String) {
        val books = this.books
        books.removeAt(position.toInt())
        update(books.toList())
    }

    fun addItem(item: Book, position: String) {
        val books = this.books
        if (position.isBlank() || (position.toInt() >= books.size)) {
            books.add(item)
            update(books.toList())
        } else {
            books.add(position.toInt(), item)
            update(books.toList())
        }
    }
}
