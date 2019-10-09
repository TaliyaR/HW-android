package com.itis.template

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
    }

    fun onClickButton(view : View){
        val intent = Intent(this, MainActivity::class.java)
        PasswordRepository.password = et_change_password.text.toString().trim()
        startActivity(intent)
    }
}
