package com.example.firebaseauthpreference

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_preference.*
import kotlinx.android.synthetic.main.activity_preference.btn_login
import kotlinx.android.synthetic.main.activity_preference.btn_logout
import kotlinx.android.synthetic.main.activity_preference.btn_register
import kotlinx.android.synthetic.main.activity_preference.et_email
import kotlinx.android.synthetic.main.activity_preference.et_password
import kotlinx.android.synthetic.main.activity_preference.tv_message

class PreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()

        btn_register.setOnClickListener {
            editor.putString(et_email.text.toString(),et_password.text.toString())
            editor.apply()
            Toast.makeText(this, "Register Success", Toast.LENGTH_LONG).show()
        }

        btn_login.setOnClickListener {
            val pwd = sharedPreference.getString(et_email.text.toString(),"null")
            if (et_password.text.toString() == pwd) {
                signInSuccess()
            }else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
            }
        }

        btn_logout.setOnClickListener {
            signOut()
        }

        btn_switch.setOnClickListener {
            val intent = Intent (this, PreferenceActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signInSuccess() {
        Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show()
        tv_message.text = et_email.text.toString()
        et_email.text.clear()
        et_password.text.clear()
        btn_logout.visibility = View.VISIBLE
        btn_login.visibility = View.GONE
        btn_register.visibility = View.GONE
        et_email.visibility = View.GONE
        et_password.visibility = View.GONE
    }

    private fun signOut() {
        btn_logout.visibility = View.GONE
        btn_login.visibility = View.VISIBLE
        btn_register.visibility = View.VISIBLE
        et_email.visibility = View.VISIBLE
        et_password.visibility = View.VISIBLE

        tv_message.text = "Please Login"
    }
}