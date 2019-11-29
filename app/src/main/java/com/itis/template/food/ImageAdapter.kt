package com.itis.template.food

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.itis.template.R
import kotlinx.android.synthetic.main.item_food.view.*


class ImageAdapter(var context: Context, var images: List<Int>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(context).inflate(R.layout.item_food, container, false)
        layoutInflater.iv_image.setImageResource(images[position])
        container.addView(layoutInflater, 0)
        return layoutInflater
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    companion object {
        fun imageAdapter(context: Context, images: List<Int>): ImageAdapter = ImageAdapter(context, images)
    }
}
