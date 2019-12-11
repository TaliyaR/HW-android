package com.itis.template.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.itis.template.R
import kotlinx.android.synthetic.main.fragment_description_goal.*

class DescriptionGoalFragment : Fragment() {

    companion object {

        private const val GOAL = "ARG_GOAL"
        private const val DESCRIPTION = "ARG_DESCRIPTION"
        private const val PLAN = "ARG_PLAN"

        fun newInstance(goal: String, description: String, plan: String
        ): DescriptionGoalFragment = DescriptionGoalFragment().apply {
            arguments = Bundle().apply {
                putString(GOAL, goal)
                putString(DESCRIPTION, description)
                putString(PLAN, plan)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.desc_goal)
        return inflater.inflate(R.layout.fragment_description_goal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_goal.text = arguments?.getString(GOAL) ?: ""
        tv_description.text = arguments?.getString(DESCRIPTION) ?: ""
        tv_plan.text = arguments?.getString(PLAN) ?: ""
    }
}
