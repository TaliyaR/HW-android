package com.itis.template.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.itis.template.Book
import com.itis.template.R
import kotlinx.android.synthetic.main.fragment_add_dialog.*
import kotlinx.android.synthetic.main.fragment_add_dialog.view.*


class AddDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialogView = LayoutInflater.from(context).inflate(R.layout.fragment_add_dialog, null)

        var builder = AlertDialog.Builder(context).apply {
            setTitle("Add item")
            setView(dialogView)
        }
//        val alertDialog = builder.show()

        cancel_btn.setOnClickListener {
            dismiss()
        }
        add_btn.setOnClickListener {
            val book = Book(dialogView.et_title.text.toString(), dialogView.et_description.text.toString())
            val position = dialogView.et_position.text.toString()
        }
        return builder.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_dialog, container, false)
    }

    companion object {
        private const val FRAGMENT_TAG = "custom_dialog"

        fun newInstance(): AddDialogFragment = AddDialogFragment()

        fun show(fragmentManager: FragmentManager): AddDialogFragment {
            val dialog = newInstance()
            dialog.show(fragmentManager, FRAGMENT_TAG)
            return dialog
        }
    }
}
