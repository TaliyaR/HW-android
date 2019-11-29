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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv_item_food.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = FoodAdapter(getDataSource())
        }
    }

    companion object {
        fun newInstance(): CardFragment = CardFragment()
    }

    private fun getDataSource() = listOf(
            Food("Food 1", "Desc 1", R.drawable.p1, listOf(R.drawable.g, R.drawable.n, R.drawable.w)),
            Food("Food 2", "Desc 2", R.drawable.p2, listOf(R.drawable.p, R.drawable.s, R.drawable.w, R.drawable.g))
    )
}
