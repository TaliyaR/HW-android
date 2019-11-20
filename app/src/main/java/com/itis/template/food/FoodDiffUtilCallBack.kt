package com.itis.template.food

import androidx.recyclerview.widget.DiffUtil

class FoodDiffUtilCallBack(
        private val oldList: List<Food>,
        private val newList: List<Food>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].title == newList[newItemPosition].title
}
