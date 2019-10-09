package com.itis.template

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        et_sign_in_pass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                ti_sign_in_pass.error = null
                ti_sign_in_pass.isErrorEnabled = false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    fun onClickLogin(view: View){
        val logIntent = Intent(this, MainActivity::class.java)
        if(et_sign_in_pass.text.toString() == PasswordRepository.password){
            progressBar.visibility = View.VISIBLE
            startActivity(logIntent)
        }
        else{
            setPasswordError()
        }
    }

    fun onClickChange(view: View){
        val intent = Intent(this, ChangePasswordActivity::class.java)
        startActivity(intent)
    }

    private fun setPasswordError(){
        ti_sign_in_pass.error = getString(R.string.validate_password)
    }
}
