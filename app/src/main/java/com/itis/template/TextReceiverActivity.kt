package com.itis.template

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_text_receiver.*

class TextReceiverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_receiver)
        val intent: Intent = intent
        tv_message.text = intent.getStringExtra(Intent.EXTRA_TEXT)
    }
}
