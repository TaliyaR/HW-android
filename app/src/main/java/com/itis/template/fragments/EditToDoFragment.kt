package com.itis.template.fragments


import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.itis.template.AppDatabase
import com.itis.template.MainActivity
import com.itis.template.R
import com.itis.template.entity.Task
import kotlinx.android.synthetic.main.fragment_edit_to_do.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

class EditToDoFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var db: AppDatabase
    private lateinit var dbHelper: DbHelper
    private var taskUpd: Task? = null
    private lateinit var act: Context
    private lateinit var date: Date

    override fun onAttach(context: Context) {
        super.onAttach(context)
        db = AppDatabase(context)
        dbHelper = DbHelper(context)
        act = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        et_title.setText(arguments?.getString(TITLE), TextView.BufferType.EDITABLE)
        et_description.setText(arguments?.getString(DESCRIPTION), TextView.BufferType.EDITABLE)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        date_btn.setOnClickListener {
            val dpd = DatePickerDialog(act, DatePickerDialog.OnDateSetListener { _, mYear, mMonth, mDay ->
                date = Date(mYear, mMonth, mDay)
            }, year, month, day)
            dpd.show()
        }
        save_btn.setOnClickListener {
            if (taskUpd == null) {
                val title = et_title.text.toString()
                val description = et_description.text.toString()
                val date = date
                val latitude = (activity as MainActivity).latitude
                val longitude = (activity as MainActivity).longitude
                launch { db.taskDAO().insert(Task(null, title, description, date, longitude, latitude)) }
            } else {
                taskUpd?.title = et_title.text.toString()
                taskUpd?.description = et_description.text.toString()
                taskUpd?.date = date
                launch { db.taskDAO().update(taskUpd ?: return@launch) }
            }
            fragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
    }

    companion object {
        fun newInstance(task: Task): EditToDoFragment = EditToDoFragment().apply {
            taskUpd = task
            arguments = Bundle().apply {
                putString(TITLE, task.title)
                putString(DESCRIPTION, task.description)
            }
        }

        fun newInstance(): EditToDoFragment = EditToDoFragment()

        const val TITLE = "ARG_TITLE"
        const val DESCRIPTION = "ARG_DESCRIPTION"
        const val DATE = "ARG_DATE"

    }
}
