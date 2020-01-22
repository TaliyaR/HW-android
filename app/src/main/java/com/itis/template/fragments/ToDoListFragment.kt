package com.itis.template.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.itis.template.AppDatabase
import com.itis.template.R
import com.itis.template.entity.Task
import com.itis.template.rv.ToDoAdapter
import kotlinx.android.synthetic.main.fragment_to_do_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ToDoListFragment : Fragment(), CoroutineScope by MainScope() {
    private lateinit var db: AppDatabase
    private var tasksList = mutableListOf<Task>()

    private lateinit var adapter: ToDoAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        db = AppDatabase(context)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        fab.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                setCustomAnimations(
                        R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right
                )
                replace(R.id.frame_layout, EditToDoFragment.newInstance())
                addToBackStack(null)
                commit()
            }
        }
    }

    private fun initAdapter() {
        adapter = ToDoAdapter(getAllTasks(), { Task ->
            fragmentManager?.beginTransaction()?.apply {
                setCustomAnimations(
                        R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right
                )
                replace(R.id.frame_layout, EditToDoFragment.newInstance(Task))
                addToBackStack(null)
                commit()
            }
        }) {
            deleteTask(it)
        }
        adapter.notifyDataSetChanged()
        rv_todo.adapter = adapter
        rv_todo.layoutManager = GridLayoutManager(context, 2)
    }

    private fun getAllTasks(): MutableList<Task> {
        launch {
            tasksList = db.taskDAO().getAll().toMutableList()
        }
        return tasksList
    }

    private fun deleteTask(task: Task) {
        launch {
            db.taskDAO().delete(task)
            tasksList = db.taskDAO().getAll().toMutableList()
            adapter.update(tasksList)
        }
    }

    companion object {
        fun newInstance(): ToDoListFragment = ToDoListFragment()
    }
}
