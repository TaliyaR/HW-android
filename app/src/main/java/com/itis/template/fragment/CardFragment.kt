package com.itis.template.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.itis.template.R
import com.itis.template.food.Food
import com.itis.template.food.FoodAdapter
import kotlinx.android.synthetic.main.fragment_card.*

class CardFragment : Fragment() {

    private var adapter: FoodAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv_item_food.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FoodAdapter(getDataSource())
        }

    }

    companion object {
        fun newInstance(): CardFragment = CardFragment()
    }

    private fun getDataSource(): List<Food> = arrayListOf(
            Food("Food 1", "Desc 1", R.drawable.d),
            Food("Food 2", "Desc 2", R.drawable.f)
    )
}
