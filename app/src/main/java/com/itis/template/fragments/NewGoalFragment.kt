package com.itis.template.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.itis.template.R
import kotlinx.android.synthetic.main.fragment_new_goal.*

class NewGoalFragment : Fragment() {

    companion object {
        fun newInstance(): NewGoalFragment = NewGoalFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.new_goal)
        return inflater.inflate(R.layout.fragment_new_goal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_save.setOnClickListener {
            fragmentManager.also {
                it?.popBackStack()
                it?.beginTransaction()?.apply {
                    setCustomAnimations(
                            R.anim.enter_from_right,
                            R.anim.exit_to_left,
                            R.anim.enter_from_left,
                            R.anim.exit_to_right
                    )
                    replace(R.id.frameLayout, DescriptionGoalFragment.newInstance(
                            et_goal.text.toString(),
                            et_description.text.toString(),
                            et_plan.text.toString()
                    ))
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }
}
