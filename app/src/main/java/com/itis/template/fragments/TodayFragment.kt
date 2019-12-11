package com.itis.template.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.itis.template.R

class TodayFragment : Fragment() {

    companion object {
        fun newInstance(): TodayFragment = TodayFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.menu_today)
        return inflater.inflate(R.layout.fragment_today, container, false)
    }
}
