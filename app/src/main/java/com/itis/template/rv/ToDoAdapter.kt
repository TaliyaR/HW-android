package com.itis.template.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.itis.template.entity.Task

class ToDoAdapter(private var tasks: MutableList<Task>,
                  private val clickLambda: (Task) -> Unit,
                  private val deleteLambda: (Task) -> Unit
) : RecyclerView.Adapter<ToDoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoHolder =
            ToDoHolder.create(parent, clickLambda, deleteLambda)

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: ToDoHolder, position: Int) = holder.bind(tasks[position])


    fun update(newList: MutableList<Task>) {
        val diffCallBack = ToDoDiffUtillCallBack(tasks, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        tasks.apply {
            clear()
            addAll(newList)
        }
        diffResult.dispatchUpdatesTo(this)
    }
}
