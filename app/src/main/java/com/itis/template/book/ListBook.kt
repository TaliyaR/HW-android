package com.itis.template.book

object ListBook {

    private val list = ArrayList<Book>()

    init {
        list.add(Book("Book 1", "Author 1"))
        list.add(Book("Book 2", "Author 2"))
        list.add(Book("Book 3", "Author 3"))
        list.add(Book("Book 4", "Author 4"))
    }

    fun getList(): ArrayList<Book> = list

    fun deleteItem(book: Book) {
        list.remove(book)
    }

    fun size(): Int = list.size

    fun add(book: Book, position: Int) {
        list.add(position, book)
    }

    fun add(book: Book) {
        list.add(book)
    }

}
