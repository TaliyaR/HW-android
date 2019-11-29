package com.itis.template.fragment


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itis.template.R
import com.itis.template.book.Book
import com.itis.template.book.BookAdapter
import com.itis.template.book.ListBook
import kotlinx.android.synthetic.main.fragment_add_dialog.view.*
import kotlinx.android.synthetic.main.fragment_list.*
import java.lang.Integer.parseInt

class ListsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterList = BookAdapter(ArrayList<Book>().apply { addAll(ListBook.getList()) })

        rv_item.adapter = adapterList

        floating_btn.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(view.context)
            val dialogView = LayoutInflater.from(view.context).inflate(R.layout.fragment_add_dialog, null)
            dialogBuilder.setView(dialogView)
                    .setPositiveButton(R.string.ok) { _, _ ->
                        if (dialogView.et_position.text.toString() == "" || parseInt(dialogView.et_position.text.toString()) > ListBook.size()) {
                            ListBook.add(Book(dialogView.et_title.text.toString(), dialogView.et_description.text.toString()))
                        } else {
                            ListBook.add(Book(dialogView.et_title.text.toString(), dialogView.et_description.text.toString()), dialogView.et_position.text.toString().toInt() - 1)
                        }
                        adapterList.update()
                    }
                    .setNegativeButton(R.string.cancel) { dialog, _ ->
                        dialog.dismiss()
                    }
            dialogBuilder.create()
            dialogBuilder.show()
        }
    }

    companion object {
        fun newInstance(): ListsFragment = ListsFragment()
    }
}
