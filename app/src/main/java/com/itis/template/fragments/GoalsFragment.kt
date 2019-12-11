package com.itis.template.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.itis.template.R
import kotlinx.android.synthetic.main.fragment_goals.*

class GoalsFragment : Fragment() {

    companion object {
        fun newInstance(): GoalsFragment = GoalsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.menu_goals)
        return inflater.inflate(R.layout.fragment_goals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_next.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                setCustomAnimations(
                        R.anim.fade_in,
                        R.anim.fade_out,
                        R.anim.enter_from_top,
                        R.anim.exit_to_bottom
                )
                replace(R.id.frameLayout, NewGoalFragment.newInstance())
                addToBackStack(null)
                commit()
            }
        }
    }
}
