package com.itis.template

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.item_landmark.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val name = intent?.extras?.getString(KEY_NAME) ?: DEFAULT_NAME
        val description = intent?.extras?.getString(KEY_DESCRIPTION) ?: DEFAULT_DESCRIPTION
        val image = intent?.extras?.getInt(KEY_IMAGE) ?: R.mipmap.ic_launcher

        tv_name.text = name
        tv_description.text = description
        iv_image.setImageResource(image)
    }

    companion object {
        private const val KEY_NAME = "name"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_IMAGE = "image"
        private const val DEFAULT_NAME = "DEFAULT NAME"
        private const val DEFAULT_DESCRIPTION = "DEFAULT DESCRIPTION"


        fun createIntent(activity: Activity, name: String, description: String, image: Int) =
                Intent(activity, DetailsActivity::class.java).apply {
                    putExtra(KEY_NAME, name)
                    putExtra(KEY_DESCRIPTION, description)
                    putExtra(KEY_IMAGE, image)
                }
    }
}
