package com.itis.template.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.itis.template.Book
import com.itis.template.BookAdapter
import com.itis.template.R
import kotlinx.android.synthetic.main.fragment_list.*

class ListsFragment : Fragment() {
    private var adapter: BookAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = BookAdapter(getDataSource()) { Book ->
            onClickDelete(Book)
        }

        rv_item.layoutManager = LinearLayoutManager(activity)
        rv_item.adapter = adapter

        floating_btn.setOnClickListener {
            AddDialogFragment.show(checkNotNull(activity).supportFragmentManager)
        }
    }

    private fun onClickDelete(book: Book) {
        adapter?.deleteItem(book)
    }

    companion object {
        fun newInstance(): ListsFragment = ListsFragment()
    }

    private fun getDataSource(): List<Book> = arrayListOf(
            Book("Капитанская дочка", "Пушкин"),
            Book("1984", "Оруэл")
    )

}
