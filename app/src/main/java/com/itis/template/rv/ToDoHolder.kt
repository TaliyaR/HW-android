package com.itis.template.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.template.R
import com.itis.template.entity.Task
import kotlinx.android.synthetic.main.item_todo.view.*
import java.text.SimpleDateFormat

class ToDoHolder(
        val containerView: View,
        private val clickLambda: (Task) -> Unit,
        private val deleteLambda: (Task) -> Unit
) : RecyclerView.ViewHolder(containerView) {

    fun bind(task: Task) {

        with(containerView) {
            val format = SimpleDateFormat("dd.MM.yyy")

            tv_title.text = task.title
            tv_description.text = task.description
            tv_date.text = format.format(task.date)


            del_btn.setOnClickListener {
                deleteLambda(task)
            }

            itemView.setOnClickListener {
                clickLambda(task)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (Task) -> Unit, deleteLambda: (Task) -> Unit) =
                ToDoHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false),
                        clickLambda,
                        deleteLambda
                )
    }
}
