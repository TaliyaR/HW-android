package com.itis.template.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.itis.template.R

class ReportFragment : Fragment() {

    companion object {
        fun newInstance(): ReportFragment = ReportFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.menu_report)
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

}
